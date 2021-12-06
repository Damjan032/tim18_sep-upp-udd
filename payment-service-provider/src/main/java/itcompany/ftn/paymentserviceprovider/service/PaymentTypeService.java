package itcompany.ftn.paymentserviceprovider.service;

import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;
import org.springframework.http.HttpStatus;

public interface PaymentTypeService {

    HttpStatus addPaymentTypeViaCard(String userId, String bankName, String merchantId, String merchantPassword);

    boolean removePaymentType(String userId, PaymentType paymentType);
}
