package itcompany.ftn.paymentserviceprovider.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WebShopRegistrationDTO {

    @NotNull
    @NotBlank
    private String name;

    private String parentWebShopId;

    private String storeTransactionSuccessWebhook;

    private String storeTransactionFailureWebhook;

    private String storeTransactionErrorWebhook;

    public WebShopRegistrationDTO() {}

    public WebShopRegistrationDTO(@NotNull @NotBlank String name, String parentWebShopId, String storeTransactionSuccessWebhook, String storeTransactionFailureWebhook, String storeTransactionErrorWebhook) {
        this.name = name;
        this.parentWebShopId = parentWebShopId;
        this.storeTransactionSuccessWebhook = storeTransactionSuccessWebhook;
        this.storeTransactionFailureWebhook = storeTransactionFailureWebhook;
        this.storeTransactionErrorWebhook = storeTransactionErrorWebhook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentWebShopId() {
        return parentWebShopId;
    }

    public void setParentWebShopId(String parentWebShopId) {
        this.parentWebShopId = parentWebShopId;
    }

    public String getStoreTransactionSuccessWebhook() {
        return storeTransactionSuccessWebhook;
    }

    public void setStoreTransactionSuccessWebhook(String storeTransactionSuccessWebhook) {
        this.storeTransactionSuccessWebhook = storeTransactionSuccessWebhook;
    }

    public String getStoreTransactionFailureWebhook() {
        return storeTransactionFailureWebhook;
    }

    public void setStoreTransactionFailureWebhook(String storeTransactionFailureWebhook) {
        this.storeTransactionFailureWebhook = storeTransactionFailureWebhook;
    }

    public String getStoreTransactionErrorWebhook() {
        return storeTransactionErrorWebhook;
    }

    public void setStoreTransactionErrorWebhook(String storeTransactionErrorWebhook) {
        this.storeTransactionErrorWebhook = storeTransactionErrorWebhook;
    }
}
