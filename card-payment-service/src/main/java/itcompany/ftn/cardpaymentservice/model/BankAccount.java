package itcompany.ftn.cardpaymentservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount {

    @Id
    private String webShopId;

    @Column(nullable = false)
    private String merchantId;

    @Column(nullable = false)
    private String merchantPassword;

    @Column(nullable = false)
    private String bankUri;

    public BankAccount() {}

    public BankAccount(String webShopId, String merchantId, String merchantPassword, String bankUri) {
        this.webShopId = webShopId;
        this.merchantId = merchantId;
        this.merchantPassword = merchantPassword;
        this.bankUri = bankUri;
    }

    public String getWebShopId() {
        return webShopId;
    }

    public void setWebShopId(String webShopId) {
        this.webShopId = webShopId;
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

    public String getBankUri() {
        return bankUri;
    }

    public void setBankUri(String bankUri) {
        this.bankUri = bankUri;
    }
}
