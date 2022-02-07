package itcompany.ftn.bank2api.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String bankAccountCurrency;

    @Column(unique = true)
    private String merchantId;

    @Column
    private String merchantPassword; // TODO: hide in db

    @OneToMany
    private Set<BankCard> bankCards = new HashSet<>();

    @OneToMany
    private List<Invoice> invoices = new ArrayList<>();

    public BankAccount() {}

    public BankAccount(String id, BigDecimal balance, String bankAccountCurrency, String merchantId, String merchantPassword, Set<BankCard> bankCards, List<Invoice> invoices) {
        this.id = id;
        this.balance = balance;
        this.bankAccountCurrency = bankAccountCurrency;
        this.merchantId = merchantId;
        this.merchantPassword = merchantPassword;
        this.bankCards = bankCards;
        this.invoices = invoices;
    }

    public BankAccount(BigDecimal balance, String bankAccountCurrency, String merchantId, String merchantPassword) {
        this.balance = balance;
        this.bankAccountCurrency = bankAccountCurrency;
        this.merchantId = merchantId;
        this.merchantPassword = merchantPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<BankCard> getBankCards() {
        return bankCards;
    }

    public void setBankCards(Set<BankCard> bankCards) {
        this.bankCards = bankCards;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
