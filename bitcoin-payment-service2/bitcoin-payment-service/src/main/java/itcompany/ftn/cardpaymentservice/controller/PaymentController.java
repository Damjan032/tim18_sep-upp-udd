package itcompany.ftn.bitcoinpaymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/card-payment-service/payment")
public class PaymentController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<BankCardPaymentInfoDTO> createInvoice(@Valid @RequestBody BankCardCreateInvoiceDTO dto) {
        return invoiceService.createCardPaymentFromInvoice(dto);
    }
}
