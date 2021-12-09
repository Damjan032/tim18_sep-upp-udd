package itcompany.ftn.bank1api.controller;

import itcompany.ftn.bank1api.dto.BankCardInvoiceDTO;
import itcompany.ftn.bank1api.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.bank1api.service.BankAccountService;
import itcompany.ftn.bank1api.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/invoice")
@CrossOrigin
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<Object> createInvoice(@Valid @RequestBody BankCardInvoiceDTO dto) {
        boolean valid = bankAccountService.validateMerchantInformation(dto.getMerchantId(), dto.getMerchantPassword());
        if (!valid)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BankCardPaymentInfoDTO bankCardPaymentInfoDTO = invoiceService.getBankCardPaymentInfo(dto);
        return new ResponseEntity<>(bankCardPaymentInfoDTO, HttpStatus.OK);
    }
}
