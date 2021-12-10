package itcompany.ftn.bank1api.controller;

import itcompany.ftn.bank1api.dto.BankAccountDTO;
import itcompany.ftn.bank1api.model.BankAccount;
import itcompany.ftn.bank1api.model.BankCard;
import itcompany.ftn.bank1api.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/bank-account")
@CrossOrigin
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;


    @PostMapping
    public ResponseEntity registerBankAccount(@RequestBody BankAccountDTO dto) {
        BankAccount bankAccount = new BankAccount(dto.getBalance(), dto.getBankAccountCurrency(), dto.getMerchantId(), dto.getMerchantPassword());
        bankAccountService.save(bankAccount);

        BankCard bankCard = new BankCard(dto.getBankCardInfo().getCardHolderName(), dto.getBankCardInfo().getPanNumber(), dto.getBankCardInfo().getExpiratoryDate(), bankAccount);
        bankAccountService.saveBankCard(bankCard);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
