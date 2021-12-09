package itcompany.ftn.paymentserviceprovider.service;

import itcompany.ftn.paymentserviceprovider.dto.InvoiceDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;

public interface InvoiceService {

    Invoice createInvoice(InvoiceDTO invoiceRequestDTO);

    Invoice getById(String id);
}
