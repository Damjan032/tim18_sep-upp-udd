package itcompany.ftn.bank2api.service;

import itcompany.ftn.bank2api.dto.BankCardInvoiceDTO;
import itcompany.ftn.bank2api.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.bank2api.model.Invoice;

public interface InvoiceService {
    BankCardPaymentInfoDTO getBankCardPaymentInfo(BankCardInvoiceDTO dto);

    Invoice getById(String invoiceId);
}
