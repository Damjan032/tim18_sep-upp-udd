package itcompany.ftn.bank2api.controller;

import itcompany.ftn.bank2api.dto.BankCardInvoiceDTO;
import itcompany.ftn.bank2api.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.bank2api.model.enums.LogMode;
import itcompany.ftn.bank2api.model.enums.LogStatus;
import itcompany.ftn.bank2api.service.BankAccountService;
import itcompany.ftn.bank2api.service.InvoiceService;
import itcompany.ftn.bank2api.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.InetAddress;

@RestController
@RequestMapping(value = "/api/invoice")
@CrossOrigin
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<Object> createInvoice(@Valid @RequestBody BankCardInvoiceDTO dto) {
        boolean valid = bankAccountService.validateMerchantInformation(dto.getMerchantId(), dto.getMerchantPassword());
        if (!valid) {
            this.logger.writeLogs(LogMode.REGULAR, LogStatus.ERROR, "Create Invoice ERROR", InetAddress.getLoopbackAddress().getHostAddress());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BankCardPaymentInfoDTO bankCardPaymentInfoDTO = invoiceService.getBankCardPaymentInfo(dto);

        this.logger.writeLogs(LogMode.REGULAR, LogStatus.SUCCESS, "Create Invoice SUCCESS", InetAddress.getLoopbackAddress().getHostAddress());
        return new ResponseEntity<>(bankCardPaymentInfoDTO, HttpStatus.OK);
    }
}
