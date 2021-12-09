package itcompany.ftn.paymentserviceprovider.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class InvoiceItem {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String storeIssuedId;

    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    public InvoiceItem() {}

    public InvoiceItem(String id, String storeIssuedId, String name, String description, BigDecimal amount, String currency) {
        this.id = id;
        this.storeIssuedId = storeIssuedId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreIssuedId() {
        return storeIssuedId;
    }

    public void setStoreIssuedId(String storeIssuedId) {
        this.storeIssuedId = storeIssuedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
