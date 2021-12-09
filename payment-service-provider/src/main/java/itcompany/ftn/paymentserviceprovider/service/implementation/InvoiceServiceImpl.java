package itcompany.ftn.paymentserviceprovider.service.implementation;

import itcompany.ftn.paymentserviceprovider.dto.InvoiceItemDTO;
import itcompany.ftn.paymentserviceprovider.dto.InvoiceDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.InvoiceItem;
import itcompany.ftn.paymentserviceprovider.model.WebShop;
import itcompany.ftn.paymentserviceprovider.repository.InvoiceRepository;
import itcompany.ftn.paymentserviceprovider.repository.WebShopRepository;
import itcompany.ftn.paymentserviceprovider.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    WebShopRepository webShopRepository;

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
}
