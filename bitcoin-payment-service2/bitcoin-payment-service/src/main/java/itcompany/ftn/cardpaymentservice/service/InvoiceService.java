package itcompany.ftn.bitcoinpaymentservice.service;

import org.springframework.http.ResponseEntity;

public interface InvoiceService {

    ResponseEntity<BankCardPaymentInfoDTO> createCardPaymentFromInvoice(BankCardCreateInvoiceDTO dto);
}
