package itcompany.ftn.cardpaymentservice.service;

import itcompany.ftn.cardpaymentservice.dto.BankCardCreateInvoiceDTO;
import itcompany.ftn.cardpaymentservice.dto.BankCardPaymentInfoDTO;
import org.springframework.http.ResponseEntity;

public interface InvoiceService {

    ResponseEntity<BankCardPaymentInfoDTO> createCardPaymentFromInvoice(BankCardCreateInvoiceDTO dto);
}
