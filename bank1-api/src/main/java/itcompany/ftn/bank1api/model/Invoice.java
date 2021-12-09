package itcompany.ftn.bank1api.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    private BankAccount merchantBankAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String merchantOrderId;

    @Column(nullable = false)
    private LocalDateTime merchantTimestamp;

    @Column(nullable = false)
    private String successRedirectUrl;

    @Column(nullable = false)
    private String failureRedirectUrl;

    @Column(nullable = false)
    private String errorRedirectUrl;

    @OneToOne
    private Transaction transaction;

    public Invoice() {}

    public Invoice(String id, BankAccount merchantBankAccount, BigDecimal amount, String currency, String merchantOrderId, LocalDateTime merchantTimestamp, String successRedirectUrl, String failureRedirectUrl, String errorRedirectUrl, Transaction transaction) {
        this.id = id;
        this.merchantBankAccount = merchantBankAccount;
        this.amount = amount;
        this.currency = currency;
        this.merchantOrderId = merchantOrderId;
        this.merchantTimestamp = merchantTimestamp;
        this.successRedirectUrl = successRedirectUrl;
        this.failureRedirectUrl = failureRedirectUrl;
        this.errorRedirectUrl = errorRedirectUrl;
        this.transaction = transaction;
    }

    public Invoice(BankAccount merchantBankAccount, BigDecimal amount, String currency, String merchantOrderId, LocalDateTime merchantTimestamp, String successRedirectUrl, String failureRedirectUrl, String errorRedirectUrl) {
        this.id = id;
        this.merchantBankAccount = merchantBankAccount;
        this.amount = amount;
        this.currency = currency;
        this.merchantOrderId = merchantOrderId;
        this.merchantTimestamp = merchantTimestamp;
        this.successRedirectUrl = successRedirectUrl;
        this.failureRedirectUrl = failureRedirectUrl;
        this.errorRedirectUrl = errorRedirectUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BankAccount getMerchantBankAccount() {
        return merchantBankAccount;
    }

    public void setMerchantBankAccount(BankAccount merchantBankAccount) {
        this.merchantBankAccount = merchantBankAccount;
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

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public LocalDateTime getMerchantTimestamp() {
        return merchantTimestamp;
    }

    public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
        this.merchantTimestamp = merchantTimestamp;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
