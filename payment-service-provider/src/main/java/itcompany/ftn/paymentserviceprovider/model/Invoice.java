package itcompany.ftn.paymentserviceprovider.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "web_shop_id")
    private WebShop webShop;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<InvoiceItem> invoiceItems = new HashSet<>();

    @OneToOne(mappedBy = "invoice")
    private Transaction customerTransaction;

    public Invoice() {}

    public Invoice(String id, BigDecimal amount, String currency, WebShop webShop, Set<InvoiceItem> invoiceItems, Transaction customerTransaction) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.webShop = webShop;
        this.invoiceItems = invoiceItems;
        this.customerTransaction = customerTransaction;
    }

    public Invoice(String id, BigDecimal amount, String currency, WebShop webShop, Set<InvoiceItem> invoiceItems) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.webShop = webShop;
        this.invoiceItems = invoiceItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public WebShop getWebShop() {
        return webShop;
    }

    public void setWebShop(WebShop webShop) {
        this.webShop = webShop;
    }

    public Set<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public Transaction getCustomerTransaction() {
        return customerTransaction;
    }

    public void setCustomerTransaction(Transaction customerTransaction) {
        this.customerTransaction = customerTransaction;
    }
}
