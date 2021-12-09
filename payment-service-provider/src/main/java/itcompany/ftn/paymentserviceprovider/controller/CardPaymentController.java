package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.BankCardPaymentResponseDTO;
import itcompany.ftn.paymentserviceprovider.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.Transaction;
import itcompany.ftn.paymentserviceprovider.service.InvoiceService;
import itcompany.ftn.paymentserviceprovider.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/payment-service-provider/card-payment")
public class CardPaymentController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "{invoiceId}")
    public ResponseEntity<BankCardPaymentInfoDTO> payInvoiceViaBankCard(@PathVariable String invoiceId) {
        Invoice invoice = invoiceService.getById(invoiceId);
        if (invoice == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        BankCardPaymentInfoDTO response = invoiceService.payInvoiceViaBankCard(invoice);
        if (response == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "{invoiceId}/success")
    public ResponseEntity setCardInvoiceSuccess(@RequestBody BankCardPaymentResponseDTO dto, @PathVariable String invoiceId) {
        Invoice invoice = invoiceService.getById(invoiceId);
        if (invoice == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Transaction transaction = transactionService.updateTransactionStatus(invoice, dto.getTransactionStatus());
        // TODO: Log transaction status change!

        return new ResponseEntity<>(invoice.getWebShop().getTransactionSuccessWebhook(), HttpStatus.OK);
    }

    @PostMapping(path = "{invoiceId}/failure", produces = "application/json")
    public ResponseEntity setCardInvoiceFailure(@RequestBody BankCardPaymentResponseDTO dto, @PathVariable String invoiceId) {
        Invoice invoice = invoiceService.getById(invoiceId);
        if (invoice == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Transaction transaction = transactionService.updateTransactionStatus(invoice, dto.getTransactionStatus());
        // TODO: Log transaction status change!

        return new ResponseEntity<>(invoice.getWebShop().getTransactionFailureWebhook(), HttpStatus.OK);
    }

    @PostMapping(path = "{invoiceId}/error", produces = "application/json")
    public ResponseEntity setCardInvoiceError(@RequestBody BankCardPaymentResponseDTO dto, @PathVariable String invoiceId) {
        Invoice invoice = invoiceService.getById(invoiceId);
        if (invoice == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Transaction transaction = transactionService.updateTransactionStatus(invoice, dto.getTransactionStatus());
        // TODO: Log transaction status change!

        return new ResponseEntity<>(invoice.getWebShop().getTransactionErrorWebhook(), HttpStatus.OK);
    }
}
