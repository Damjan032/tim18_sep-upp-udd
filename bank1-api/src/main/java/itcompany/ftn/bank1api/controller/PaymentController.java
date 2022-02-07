package itcompany.ftn.bank1api.controller;

import itcompany.ftn.bank1api.dto.BankCardInfoDTO;
import itcompany.ftn.bank1api.dto.PCCReqDTO;
import itcompany.ftn.bank1api.dto.PCCResDTO;
import itcompany.ftn.bank1api.model.BankAccount;
import itcompany.ftn.bank1api.model.BankCard;
import itcompany.ftn.bank1api.model.Invoice;
import itcompany.ftn.bank1api.model.Transaction;
import itcompany.ftn.bank1api.model.TransactionStatus;
import itcompany.ftn.bank1api.model.enums.LogMode;
import itcompany.ftn.bank1api.model.enums.LogStatus;
import itcompany.ftn.bank1api.repository.BankAccountRepository;
import itcompany.ftn.bank1api.repository.BankCardRepository;
import itcompany.ftn.bank1api.repository.TransactionRepository;
import itcompany.ftn.bank1api.service.InvoiceService;
import itcompany.ftn.bank1api.service.PaymentService;
import itcompany.ftn.bank1api.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    InvoiceService invoiceService;
    
    @Autowired
    BankCardRepository cardRepository;
    
    @Autowired
    BankAccountRepository accountRepository;
    
    @Autowired
    TransactionRepository transactionRepository;
    
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
    
    @PostMapping(path = "/pcc")
    public ResponseEntity<PCCResDTO> checkInvoice(@Valid @RequestBody PCCReqDTO dto) {
    	BankCard card = cardRepository.findByPanNumber(dto.getPAN());
    	BankAccount buyerAccount = card.getBankAccount();
    	
    	Transaction t = new Transaction();
    	t.setAmount(dto.getAmount());
    	t.setFromAddress(dto.getAcquirerOrderId());
    	t.setCurrency("RSD");

    	if (buyerAccount.getBalance().compareTo(dto.getAmount()) > 0) {
    		// ima dovoljno sredstava
    		t.setStatus(TransactionStatus.SUCCESS);
    		buyerAccount.setBalance(buyerAccount.getBalance().subtract(dto.getAmount())); // TODO: exchange currency
            accountRepository.save(buyerAccount);
    		
    	}
    	else {
    		// nema dovoljno sredstava
    		t.setStatus(TransactionStatus.FAILED);
    	}
    	Transaction saved = transactionRepository.save(t);
		
    	PCCResDTO response = new PCCResDTO(dto.getAcquirerOrderId(), saved.getId(),dto.getAcquirerTimestamp() , Timestamp.valueOf(saved.getTimestamp()), saved.getStatus());
    	
    	
        return new ResponseEntity<PCCResDTO>(response, HttpStatus.OK);
    }
    
}
