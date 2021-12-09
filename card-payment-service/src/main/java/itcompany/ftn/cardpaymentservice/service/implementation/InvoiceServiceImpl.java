package itcompany.ftn.cardpaymentservice.service.implementation;

import itcompany.ftn.cardpaymentservice.dto.BankCardCreateInvoiceDTO;
import itcompany.ftn.cardpaymentservice.dto.BankCardInvoiceDTO;
import itcompany.ftn.cardpaymentservice.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.cardpaymentservice.model.BankAccount;
import itcompany.ftn.cardpaymentservice.repository.BankAccountRepository;
import itcompany.ftn.cardpaymentservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Bean("cardInvoicePaymentRestTemplate")
    public RestTemplate cardInvoicePaymentRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    @Qualifier("cardInvoicePaymentRestTemplate")
    private RestTemplate cardInvoicePaymentRestTemplate;

    @Override
    public ResponseEntity<BankCardPaymentInfoDTO> createCardPaymentFromInvoice(BankCardCreateInvoiceDTO dto) {
        BankAccount bankAccount = bankAccountRepository.getById(dto.getWebShopId());
        if (bankAccount == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        BankCardInvoiceDTO bankCardInvoiceDTO = new BankCardInvoiceDTO(bankAccount.getMerchantId(), bankAccount.getMerchantPassword(), dto.getAmount(),
                dto.getCurrency(), dto.getMerchantOrderId(), LocalDateTime.now(), dto.getSuccessRedirectUrl(),
                dto.getFailureRedirectUrl(), dto.getErrorRedirectUrl());

        try {
            return cardInvoicePaymentRestTemplate.exchange(bankAccount.getBankUri(), HttpMethod.POST,
                    new HttpEntity<>(bankCardInvoiceDTO), BankCardPaymentInfoDTO.class);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
