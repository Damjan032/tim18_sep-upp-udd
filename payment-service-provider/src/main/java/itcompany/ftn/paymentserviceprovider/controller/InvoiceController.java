package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.InvoiceDTO;
import itcompany.ftn.paymentserviceprovider.dto.PaypalBtcTransactionDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.WebShop;
import itcompany.ftn.paymentserviceprovider.service.InvoiceService;
import itcompany.ftn.paymentserviceprovider.service.TransactionService;
import itcompany.ftn.paymentserviceprovider.service.WebShopService;
import itcompany.ftn.paymentserviceprovider.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/payment-service-provider/invoice")
public class InvoiceController {

    @Autowired
    WebShopService webShopService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity createInvoice(@Valid @RequestBody InvoiceDTO dto) {
        Invoice invoice = invoiceService.createInvoice(dto);
        if (invoice == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // redirection on FE page with Invoice display
        return new ResponseEntity<>(String.format("https://localhost:4201/invoice/%s", invoice.getId()), HttpStatus.OK);
    }

    @GetMapping(path = "/{invoiceId}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable String invoiceId) {
        Invoice invoice = invoiceService.getById(invoiceId);
        if (invoice == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(EntityMapper.invoiceToInvoiceDTO(invoice), HttpStatus.OK);
    }

    @PostMapping("/pp-btc-transaction")
    public ResponseEntity<String> saveTransactionPspBtc(@RequestBody PaypalBtcTransactionDTO paypalBtcTransactionDTO){
        Invoice invoice = invoiceService.getById(paypalBtcTransactionDTO.getInvoiceId());
        WebShop webShop = webShopService.getById("4028808c7dac61d3017dac9517690001");
        transactionService.addBtcOrPPTransaction(invoice, webShop, paypalBtcTransactionDTO);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


}
