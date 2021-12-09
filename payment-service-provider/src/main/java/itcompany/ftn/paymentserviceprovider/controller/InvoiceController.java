package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.InvoiceDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.service.InvoiceService;
import itcompany.ftn.paymentserviceprovider.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/payment-service-provider/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

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
}