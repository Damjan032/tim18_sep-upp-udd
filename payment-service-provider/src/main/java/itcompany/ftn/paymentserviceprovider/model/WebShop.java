package itcompany.ftn.paymentserviceprovider.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WebShop {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private String transactionSuccessWebhook;

    private String transactionFailureWebhook;

    private String transactionErrorWebhook;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private WebShop parentWebShop;

    @OneToMany(mappedBy = "parentWebShop", cascade = CascadeType.ALL)
    private Set<WebShop> subWebShops = new HashSet<>();

    @OneToOne(mappedBy = "managedWebShop")
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PaymentTypes", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<PaymentType> chosenPaymentTypes = new HashSet<>();

    public WebShop() {}

    public WebShop(String id, String name, String transactionSuccessWebhook, String transactionFailureWebhook, String transactionErrorWebhook, WebShop parentWebShop, Set<WebShop> subWebShops, User user, Set<PaymentType> chosenPaymentTypes) {
        this.id = id;
        this.name = name;
        this.transactionSuccessWebhook = transactionSuccessWebhook;
        this.transactionFailureWebhook = transactionFailureWebhook;
        this.transactionErrorWebhook = transactionErrorWebhook;
        this.parentWebShop = parentWebShop;
        this.subWebShops = subWebShops;
        this.user = user;
        this.chosenPaymentTypes = chosenPaymentTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransactionSuccessWebhook() {
        return transactionSuccessWebhook;
    }

    public void setTransactionSuccessWebhook(String transactionSuccessWebhook) {
        this.transactionSuccessWebhook = transactionSuccessWebhook;
    }

    public String getTransactionFailureWebhook() {
        return transactionFailureWebhook;
    }

    public void setTransactionFailureWebhook(String transactionFailureWebhook) {
        this.transactionFailureWebhook = transactionFailureWebhook;
    }

    public String getTransactionErrorWebhook() {
        return transactionErrorWebhook;
    }

    public void setTransactionErrorWebhook(String transactionErrorWebhook) {
        this.transactionErrorWebhook = transactionErrorWebhook;
    }

    public WebShop getParentWebShop() {
        return parentWebShop;
    }

    public void setParentWebShop(WebShop parentWebShop) {
        this.parentWebShop = parentWebShop;
    }

    public Set<WebShop> getSubWebShops() {
        return subWebShops;
    }

    public void setSubWebShops(Set<WebShop> subWebShops) {
        this.subWebShops = subWebShops;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<PaymentType> getChosenPaymentTypes() {
        return chosenPaymentTypes;
    }

    public void setChosenPaymentTypes(Set<PaymentType> chosenPaymentTypes) {
        this.chosenPaymentTypes = chosenPaymentTypes;
    }
}
