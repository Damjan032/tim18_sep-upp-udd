package itcompany.ftn.bitcoinpaymentservice.controller;

import itcompany.ftn.bitcoinpaymentservice.dto.BitcoinPaymentDTO;
import itcompany.ftn.bitcoinpaymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/bitcoin-payment-service2/test")
public class TestController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        System.out.println("EVE ME ODJE");
        return new ResponseEntity<>("Response from card-payment-service", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> test(@RequestBody String pera){
        System.out.println(pera);
        System.out.println("EVE");
        return new ResponseEntity<>("Response from card-payment-service", HttpStatus.OK);
    }

    @PostMapping(value = "/pay")
    public ResponseEntity<String> pay(@Valid @RequestBody BitcoinPaymentDTO paymentDataDto) {
        System.out.println("Payment requested");
        //logger.info("Payment requested");
        try {
            return ResponseEntity.ok(paymentService.pay(paymentDataDto));
        } catch (Exception e) {
           // logger.trace(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}