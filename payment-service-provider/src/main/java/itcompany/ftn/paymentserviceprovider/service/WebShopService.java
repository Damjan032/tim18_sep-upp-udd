package itcompany.ftn.paymentserviceprovider.service;

import itcompany.ftn.paymentserviceprovider.model.WebShop;
import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;

import java.util.Set;

public interface WebShopService {

    WebShop getById(String id);

    WebShop register(String name, String userId, String parentWebShopId, String successWebhook, String failureWebhook, String errorWebhook);

    WebShop save(WebShop store);

    void removePaymentType(String id, PaymentType card);

    void addPaymentType(String id, PaymentType card);

    Set<PaymentType> getSupportedPaymentTypesByUserId(String userId);
}
