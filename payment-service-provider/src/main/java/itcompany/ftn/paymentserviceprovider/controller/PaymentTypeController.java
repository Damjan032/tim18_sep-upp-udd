package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.CardPaymentTypeDTO;
import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;
import itcompany.ftn.paymentserviceprovider.service.PaymentTypeService;
import itcompany.ftn.paymentserviceprovider.service.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "api/payment-service-provider/payment-type")
public class PaymentTypeController {

    @Autowired
    PaymentTypeService paymentTypeService;

    @Autowired
    WebShopService webShopService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_WEB_SHOP_ADMIN', 'ROLE_WEB_SHOP_MERCHANT')")
    public ResponseEntity<Set<PaymentType>> getSupportedPaymentTypes()
    {
        String userId = getCurrentUserId();
        Set<PaymentType> supportedPaymentTypes = webShopService.getSupportedPaymentTypesByUserId(userId);
        if (supportedPaymentTypes == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(supportedPaymentTypes, HttpStatus.OK);
    }

    @PostMapping(path="card")
    @PreAuthorize("hasAnyRole('ROLE_WEB_SHOP_ADMIN', 'ROLE_WEB_SHOP_MERCHANT')")
    public ResponseEntity addCardPaymentType(@Valid @RequestBody CardPaymentTypeDTO dto) {
        try {
            boolean success = paymentTypeService.addPaymentTypeViaCard(getCurrentUserId(), dto.getBankName(), dto.getMerchantId(), dto.getMerchantPassword());
            if (!success)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_WEB_SHOP_ADMIN', 'ROLE_WEB_SHOP_MERCHANT')")
    public ResponseEntity deleteCardPaymentOption(@RequestParam("paymentType") PaymentType paymentType) {
        paymentTypeService.removePaymentType(getCurrentUserId(), paymentType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
