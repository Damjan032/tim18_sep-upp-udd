package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.paymentserviceprovider.dto.SignInInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/payment-service-provider/bitcoin-payment")
public class BitcoinController {

    @LoadBalanced
    @Bean("bitcoinInvoicePaymentRestTemplate")
    public RestTemplate cardInvoicePaymentRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    @Qualifier("bitcoinInvoicePaymentRestTemplate")
    private RestTemplate bitcoinInvoicePaymentRestTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        System.out.println("USAO OVDE");
        String pera = "pear";
        bitcoinInvoicePaymentRestTemplate.exchange(String.format("%s/api/bitcoin-payment-service2/test",
                "http://gateway-service"),
                HttpMethod.POST, new HttpEntity<>(pera), String.class);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
