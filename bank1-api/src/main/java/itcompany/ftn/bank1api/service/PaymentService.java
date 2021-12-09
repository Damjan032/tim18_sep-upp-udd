package itcompany.ftn.bank1api.service;

import itcompany.ftn.bank1api.dto.BankCardInfoDTO;

public interface PaymentService {
    boolean payInvoice(String invoiceId, BankCardInfoDTO bankCardInfoDTO);

    void notifyClientException(String invoiceId);

    void notifySuccess(String invoiceId);

    void notifyServerException(String invoiceId);
}
