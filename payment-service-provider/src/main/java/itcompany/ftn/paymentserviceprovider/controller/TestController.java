package itcompany.ftn.paymentserviceprovider.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/payment-service-provider/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Response from payment-service-provider", HttpStatus.OK);
    }
}
