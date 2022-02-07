package itcompany.ftn.paypalpaymentservice.controller;

import itcompany.ftn.paypalpaymentservice.DTO.OrderDTO;
import itcompany.ftn.paypalpaymentservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping(value = "api/paypal-payment-service/v1/test")
public class PaypalController {

    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

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

    @PostMapping("/pay")
    public ResponseEntity<String> payment(@RequestBody OrderDTO order) {
        try {
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:4200/fail?status=FAILED&invoceId=" + order.getInvoiceId() + "&type=PAYPAL&",
                    "http://localhost:4200/success?status=SUCCESS&invoceId=" + order.getInvoiceId() + "&type=PAYPAL&");
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return new ResponseEntity<>(link.getHref(), HttpStatus.OK);
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "http://localhost:4200/fail";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}
