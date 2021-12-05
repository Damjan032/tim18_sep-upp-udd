package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.WebShopRegistrationDTO;
import itcompany.ftn.paymentserviceprovider.model.WebShop;
import itcompany.ftn.paymentserviceprovider.service.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/payment-service-provider/web-shop")
public class WebShopController {

    @Autowired
    WebShopService webShopService;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity registerWebShop(@Valid @RequestBody WebShopRegistrationDTO dto) {
        WebShop webShop = webShopService.register(dto.getName(), this.getCurrentUserId(), dto.getParentWebShopId(),
                dto.getStoreTransactionSuccessWebhook(), dto.getStoreTransactionFailureWebhook(), dto.getStoreTransactionErrorWebhook());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
