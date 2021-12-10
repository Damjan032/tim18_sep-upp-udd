package itcompany.ftn.bank1api.service;

import itcompany.ftn.bank1api.dto.BankCardInfoDTO;
import itcompany.ftn.bank1api.model.Invoice;

public interface PaymentService {
    boolean payInvoice(Invoice invoice, BankCardInfoDTO bankCardInfoDTO);

    void notifyClientException(Invoice invoice);

    void notifySuccess(Invoice invoiced);

    void notifyServerException(Invoice invoiced);
}
