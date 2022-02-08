package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.BitcoinPaymentDTO;
import itcompany.ftn.paymentserviceprovider.dto.PayPalOrderDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping(value = "api/payment-service-provider/paypal-payment")
public class PayPalController {

    private static final Logger logger = LoggerFactory.getLogger(PayPalController.class);
    @Autowired
    InvoiceService invoiceService;

    @LoadBalanced
    @Bean("paypalInvoicePaymentRestTemplate")
    public RestTemplate paypalInvoicePaymentRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    @Qualifier("paypalInvoicePaymentRestTemplate")
    private RestTemplate paypalInvoicePaymentRestTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        System.out.println("USAO OVDE");
        String pera = "pear";
        paypalInvoicePaymentRestTemplate.exchange(String.format("%s/api/paypal-payment-service/v1/test",
                        "http://gateway-service"),
                HttpMethod.POST, new HttpEntity<>(pera), String.class);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }



    @PostMapping(path = "{invoiceId}")
    public ResponseEntity<String> payInvoiceViaPAypal(@PathVariable String invoiceId, @RequestBody String token) {
        logger.info("Started paypal payment transaction for " + invoiceId + " invoice");
        Invoice invoice = invoiceService.getById(invoiceId);
        if (invoice == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        System.out.println(invoice);

        PayPalOrderDTO payPalOrderDTO = PayPalOrderDTO.builder().currency("USD")
                .price(invoice.getAmount().doubleValue())
                .invoiceId(invoiceId)
                .description("Descriptio")
                .intent("SALE")
                .method("payPal")
                .build();

        ResponseEntity<String> urlResponse = paypalInvoicePaymentRestTemplate.exchange(String.format("%s/api/paypal-payment-service/v1/test/pay",
                        "http://gateway-service"),
                HttpMethod.POST, new HttpEntity<>(payPalOrderDTO), String.class);

        if (urlResponse.getBody() != null) {
            System.out.println(urlResponse.getBody());
            return new ResponseEntity<>(urlResponse.getBody(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
