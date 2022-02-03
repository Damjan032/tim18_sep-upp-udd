package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.paymentserviceprovider.dto.BitcoinPaymentDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@RestController
@RequestMapping(value = "api/payment-service-provider/bitcoin-payment")
public class BitcoinController {

    @Autowired
    InvoiceService invoiceService;

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



    @PostMapping(path = "{invoiceId}")
    public ResponseEntity<String> payInvoiceViaBitcoin(@PathVariable String invoiceId, @RequestBody String token) {
        System.out.println("USAO OVDE2");
        System.out.println(token);
        Invoice invoice = invoiceService.getById(invoiceId);
        if (invoice == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        System.out.println(invoice);

        BitcoinPaymentDTO bitcoinPaymentDTO = BitcoinPaymentDTO.builder().amount(invoice.getAmount().doubleValue())
                .merchantOrderId(invoiceId)
                .errorURL("http://localhost:4201/error")
                .successURL("http://localhost:4201/success")
                .failedURL("http://localhost:4201/faild")
                .merchantTimestamp(new Timestamp(System.currentTimeMillis()))
                .amount(invoice.getAmount().doubleValue())
                .token(token)
                .build();

        ResponseEntity<String> urlResponse = bitcoinInvoicePaymentRestTemplate.exchange(String.format("%s/api/bitcoin-payment-service2/test/pay",
                        "http://gateway-service"),
                HttpMethod.POST, new HttpEntity<>(bitcoinPaymentDTO), String.class);

        if (urlResponse.getBody() != null) {
            System.out.println(urlResponse.getBody());
            return new ResponseEntity<>(urlResponse.getBody(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
