package itcompany.ftn.bitcoinpaymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "api/card-payment-service/account")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createAccount(@Valid @RequestBody BankAccountDTO dto) {
        boolean success = bankAccountService.save(dto.getWebShopId(), dto.getMerchantId(), dto.getMerchantPassword(), dto.getBankName());
        if (success)
            return new ResponseEntity(HttpStatus.CREATED);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping(path = "/{webShopId}", produces = "application/json")
    public ResponseEntity deleteAccount(@PathVariable String webShopId) {
        bankAccountService.delete(webShopId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
