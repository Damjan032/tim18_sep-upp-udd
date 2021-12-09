package itcompany.ftn.bank1api.service;

import itcompany.ftn.bank1api.dto.BankCardInvoiceDTO;
import itcompany.ftn.bank1api.dto.BankCardPaymentInfoDTO;

public interface InvoiceService {
    BankCardPaymentInfoDTO getBankCardPaymentInfo(BankCardInvoiceDTO dto);
}
