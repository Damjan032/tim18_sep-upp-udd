package itcompany.ftn.paymentserviceprovider.service;

import itcompany.ftn.paymentserviceprovider.model.WebShop;

public interface WebShopService {

    WebShop getById(String id);

    WebShop register(String name, String userId, String parentWebShopId, String successWebhook, String failureWebhook, String errorWebhook);

    WebShop save(WebShop store);

}
