package itcompany.ftn.paypalpaymentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/paypal-payment-service/test")
public class TestController {


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        System.out.println("EVE ME ODJE");
        return new ResponseEntity<>("Response from card-payment-service", HttpStatus.OK);
    }


}