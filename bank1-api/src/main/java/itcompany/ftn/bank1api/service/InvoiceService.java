package itcompany.ftn.bank1api.service;

import itcompany.ftn.bank1api.dto.BankCardInvoiceDTO;
import itcompany.ftn.bank1api.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.bank1api.model.Invoice;

public interface InvoiceService {
    BankCardPaymentInfoDTO getBankCardPaymentInfo(BankCardInvoiceDTO dto);

    Invoice getById(String invoiceId);
}
