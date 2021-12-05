package itcompany.ftn.bankpaymentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/bank-payment-service/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Response from bank-payment-service", HttpStatus.OK);
    }
}
