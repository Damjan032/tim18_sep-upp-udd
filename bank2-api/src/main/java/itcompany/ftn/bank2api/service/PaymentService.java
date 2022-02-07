package itcompany.ftn.bank2api.service;

import itcompany.ftn.bank2api.dto.BankCardInfoDTO;
import itcompany.ftn.bank2api.model.Invoice;

public interface PaymentService {
    boolean payInvoice(Invoice invoice, BankCardInfoDTO bankCardInfoDTO);

    void notifyClientException(Invoice invoice);

    void notifySuccess(Invoice invoiced);

    void notifyServerException(Invoice invoiced);
}
