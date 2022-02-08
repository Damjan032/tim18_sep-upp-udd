package itcompany.ftn.bitcoinpaymentservice.controller;

import itcompany.ftn.bitcoinpaymentservice.dto.BitcoinPaymentDTO;
import itcompany.ftn.bitcoinpaymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/bitcoin-payment-service2/test")
public class BtcPaymentController {

    @Autowired
    PaymentService paymentService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        System.out.println("EVE ME ODJE");
        return new ResponseEntity<>("Response from card-payment-service", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> test(@RequestBody String pera){
        System.out.println(pera);
        return new ResponseEntity<>("Response from card-payment-service", HttpStatus.OK);
    }

    @PostMapping(value = "/pay")
    public ResponseEntity<String> pay(@Valid @RequestBody BitcoinPaymentDTO paymentDataDto) {
        logger.info("Bitcoin payment requested");
        try {
            return ResponseEntity.ok(paymentService.pay(paymentDataDto));
        } catch (Exception e) {
            logger.trace(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}