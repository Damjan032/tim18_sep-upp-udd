package itcompany.ftn.bank1api.controller;

import itcompany.ftn.bank1api.dto.BankCardInfoDTO;
import itcompany.ftn.bank1api.service.InvoiceService;
import itcompany.ftn.bank1api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    InvoiceService invoiceService;

    @PutMapping(path = "{invoiceId}")
    public ResponseEntity payInvoice(@Valid @RequestBody BankCardInfoDTO dto, @PathVariable String invoiceId) {
        try {
            boolean success = paymentService.payInvoice(invoiceId, dto);
            if (!success)
                paymentService.notifyClientException(invoiceId);
            else
                paymentService.notifySuccess(invoiceId);
        } catch (Exception e) {
            paymentService.notifyServerException(invoiceId);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
