package itcompany.ftn.paymentserviceprovider.dto;

import java.math.BigDecimal;

public class BankCardCreateInvoiceDTO {

    private String merchantOrderId;

    private String webShopId;

    private String successRedirectUrl;

    private String failureRedirectUrl;

    private String errorRedirectUrl;

    private BigDecimal amount;

    private String currency;

    public BankCardCreateInvoiceDTO() {
    }

    public BankCardCreateInvoiceDTO(String merchantOrderId, String webShopId, String successRedirectUrl, String failureRedirectUrl, String errorRedirectUrl, BigDecimal amount, String currency) {
        this.merchantOrderId = merchantOrderId;
        this.webShopId = webShopId;
        this.successRedirectUrl = successRedirectUrl;
        this.failureRedirectUrl = failureRedirectUrl;
        this.errorRedirectUrl = errorRedirectUrl;
        this.amount = amount;
        this.currency = currency;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getWebShopId() {
        return webShopId;
    }

    public void setWebShopId(String webShopId) {
        this.webShopId = webShopId;
    }

    public String getSuccessRedirectUrl() {
        return successRedirectUrl;
    }

    public void setSuccessRedirectUrl(String successRedirectUrl) {
        this.successRedirectUrl = successRedirectUrl;
    }

    public String getFailureRedirectUrl() {
        return failureRedirectUrl;
    }

    public void setFailureRedirectUrl(String failureRedirectUrl) {
        this.failureRedirectUrl = failureRedirectUrl;
    }

    public String getErrorRedirectUrl() {
        return errorRedirectUrl;
    }

    public void setErrorRedirectUrl(String errorRedirectUrl) {
        this.errorRedirectUrl = errorRedirectUrl;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
