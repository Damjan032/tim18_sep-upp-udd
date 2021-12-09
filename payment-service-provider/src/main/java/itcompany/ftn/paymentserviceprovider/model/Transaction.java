package itcompany.ftn.paymentserviceprovider.model;

import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String serviceIssuedId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigDecimal amount;

    private String url;

    @Column(nullable = false)
    private Date utcTransactionTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "web_shop_id")
    private WebShop webShop;

    private boolean settled = false;

    public Transaction() {
    }

    public Transaction(String id, String serviceIssuedId, Invoice invoice, PaymentType type, String status, BigDecimal amount, String url, Date utcTransactionTime, WebShop webShop, boolean settled) {
        this.id = id;
        this.serviceIssuedId = serviceIssuedId;
        this.invoice = invoice;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.url = url;
        this.utcTransactionTime = utcTransactionTime;
        this.webShop = webShop;
        this.settled = settled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceIssuedId() {
        return serviceIssuedId;
    }

    public void setServiceIssuedId(String serviceIssuedId) {
        this.serviceIssuedId = serviceIssuedId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUtcTransactionTime() {
        return utcTransactionTime;
    }

    public void setUtcTransactionTime(Date utcTransactionTime) {
        this.utcTransactionTime = utcTransactionTime;
    }

    public WebShop getWebShop() {
        return webShop;
    }

    public void setWebShop(WebShop webShop) {
        this.webShop = webShop;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }
}
