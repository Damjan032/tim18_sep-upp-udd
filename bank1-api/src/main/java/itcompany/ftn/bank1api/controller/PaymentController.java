package itcompany.ftn.bank1api.controller;

import itcompany.ftn.bank1api.dto.BankCardInfoDTO;
import itcompany.ftn.bank1api.model.Invoice;
import itcompany.ftn.bank1api.model.enums.LogMode;
import itcompany.ftn.bank1api.model.enums.LogStatus;
import itcompany.ftn.bank1api.service.InvoiceService;
import itcompany.ftn.bank1api.service.PaymentService;
import itcompany.ftn.bank1api.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.InetAddress;

@RestController
@RequestMapping(value = "/api/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    private Logger logger;

    @PostMapping(path = "{invoiceId}")
    public ResponseEntity payInvoice(@Valid @RequestBody BankCardInfoDTO dto, @PathVariable String invoiceId) {
        Invoice invoice = invoiceService.getById(invoiceId);
        if (invoice == null) {
            this.logger.writeLogs(LogMode.REGULAR, LogStatus.ERROR, "Pay Invoice ERROR", InetAddress.getLoopbackAddress().getHostAddress());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        /*
        try {
            boolean success = paymentService.payInvoice(invoice, dto);
            if (!success)
                paymentService.notifyClientException(invoice);
            else
                paymentService.notifySuccess(invoice);
        } catch (Exception e) {
            paymentService.notifyServerException(invoice);
        }
        */
        
        boolean success = paymentService.payInvoice(invoice, dto);
        this.logger.writeLogs(LogMode.REGULAR, LogStatus.SUCCESS, "Pay Invoice SUCCESS", InetAddress.getLoopbackAddress().getHostAddress());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
