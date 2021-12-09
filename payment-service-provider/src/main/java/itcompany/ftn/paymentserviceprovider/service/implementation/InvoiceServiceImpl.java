package itcompany.ftn.paymentserviceprovider.service.implementation;

import com.google.common.collect.Lists;
import itcompany.ftn.paymentserviceprovider.dto.BankCardCreateInvoiceDTO;
import itcompany.ftn.paymentserviceprovider.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.paymentserviceprovider.dto.InvoiceItemDTO;
import itcompany.ftn.paymentserviceprovider.dto.InvoiceDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.InvoiceItem;
import itcompany.ftn.paymentserviceprovider.model.Transaction;
import itcompany.ftn.paymentserviceprovider.model.WebShop;
import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;
import itcompany.ftn.paymentserviceprovider.repository.InvoiceRepository;
import itcompany.ftn.paymentserviceprovider.repository.TransactionRepository;
import itcompany.ftn.paymentserviceprovider.repository.WebShopRepository;
import itcompany.ftn.paymentserviceprovider.service.InvoiceService;
import itcompany.ftn.paymentserviceprovider.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    WebShopRepository webShopRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${gateway-service-uri}")
    String gatewayServiceUri;

    @LoadBalanced
    @Bean("cardInvoicePaymentRestTemplate")
    public RestTemplate cardInvoicePaymentRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    @Qualifier("cardInvoicePaymentRestTemplate")
    private RestTemplate cardInvoicePaymentRestTemplate;

    @Override
    public Invoice createInvoice(InvoiceDTO invoiceRequestDTO) {
        Invoice invoice = new Invoice();
        invoice.setAmount(invoiceRequestDTO.getAmount());
        invoice.setCurrency(invoiceRequestDTO.getCurrency());

        WebShop webShop = webShopRepository.getById(invoiceRequestDTO.getWebShopId());
        if (webShop == null)
            return null;

        invoice.setWebShop(webShop);

        for (InvoiceItemDTO itemDTO : invoiceRequestDTO.getInvoiceItems()) {
            InvoiceItem item = new InvoiceItem();
            item.setName(itemDTO.getName());
            item.setDescription(itemDTO.getDescription());
            item.setAmount(itemDTO.getAmount());
            item.setCurrency(itemDTO.getCurrency());
            invoice.getInvoiceItems().add(item);
        }

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getById(String id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public BankCardPaymentInfoDTO payInvoiceViaBankCard(Invoice invoice) {
        if (invoice.getCustomerTransaction() != null && Lists.newArrayList("success", "paid", "complete").contains(invoice.getCustomerTransaction().getStatus()))
            return null;

        BankCardCreateInvoiceDTO bankCardCreateInvoiceDTO = EntityMapper.invoiceToBankCardInvoiceDTO(
                invoice,
                String.format("%s/payment-service-provider/card-payment/%s/success", gatewayServiceUri, invoice.getId()),
                String.format("%s/payment-service-provider/card-payment/%s/failure", gatewayServiceUri, invoice.getId()),
                String.format("%s/payment-service-provider/card-payment/%s/error", gatewayServiceUri, invoice.getId())
        );

        ResponseEntity<BankCardPaymentInfoDTO> response = cardInvoicePaymentRestTemplate.exchange(
                String.format("%s/api/card-payment-service/payment", gatewayServiceUri),
                HttpMethod.POST, new HttpEntity<>(bankCardCreateInvoiceDTO), BankCardPaymentInfoDTO.class);

        if (response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
            return null;

        BankCardPaymentInfoDTO bankCardPaymentInfoDTO = response.getBody();

        Transaction transaction = new Transaction();
        transaction.setServiceIssuedId(bankCardPaymentInfoDTO.getPaymentId());
        transaction.setInvoice(invoice);
        transaction.setType(PaymentType.CARD);
        transaction.setStatus("created");
        transaction.setAmount(invoice.getAmount());
        transaction.setUrl(bankCardPaymentInfoDTO.getPaymentUrl());
        transaction.setUtcTransactionTime(new Date());
        transaction.setWebShop(invoice.getWebShop());
        transactionRepository.save(transaction);

        // TODO: Log transaction!

        return bankCardPaymentInfoDTO;
    }
}
