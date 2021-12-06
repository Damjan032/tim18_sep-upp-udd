package itcompany.ftn.paymentserviceprovider.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CardPaymentTypeDTO {

    private String webShopId;

    @NotNull
    @NotBlank
    private String bankName;

    @NotNull
    @NotBlank
    private String merchantId;

    @NotNull
    @NotBlank
    private String merchantPassword;

    public CardPaymentTypeDTO() {}

    public CardPaymentTypeDTO(String webShopId, @NotNull @NotBlank String bankName, @NotNull @NotBlank String merchantId, @NotNull @NotBlank String merchantPassword) {
        this.webShopId = webShopId;
        this.bankName = bankName;
        this.merchantId = merchantId;
        this.merchantPassword = merchantPassword;
    }

    public String getWebShopId() {
        return webShopId;
    }

    public void setWebShopId(String webShopId) {
        this.webShopId = webShopId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }
}
