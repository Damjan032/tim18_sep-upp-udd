package itcompany.ftn.bank1api.dto;

import java.math.BigDecimal;

public class BankAccountDTO {

    private BigDecimal balance;

    private String bankAccountCurrency;

    private String merchantId;

    private String merchantPassword;

    private BankCardInfoDTO bankCardInfo;

    public BankAccountDTO() {}

    public BankAccountDTO(BigDecimal balance, String bankAccountCurrency, String merchantId, String merchantPassword, BankCardInfoDTO bankCardInfo) {
        this.balance = balance;
        this.bankAccountCurrency = bankAccountCurrency;
        this.merchantId = merchantId;
        this.merchantPassword = merchantPassword;
        this.bankCardInfo = bankCardInfo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBankAccountCurrency() {
        return bankAccountCurrency;
    }

    public void setBankAccountCurrency(String bankAccountCurrency) {
        this.bankAccountCurrency = bankAccountCurrency;
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

    public BankCardInfoDTO getBankCardInfo() {
        return bankCardInfo;
    }

    public void setBankCardInfo(BankCardInfoDTO bankCardInfo) {
        this.bankCardInfo = bankCardInfo;
    }
}
