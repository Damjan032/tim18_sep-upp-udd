package itcompany.ftn.bank2api.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class BankCard {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String cardHolderName;

    @Column(unique = true, nullable = false)
    private String panNumber; // TODO: hide in db

    @Column(nullable = false)
    private String expiratoryDate;

    @ManyToOne
    private BankAccount bankAccount;

    public BankCard() {}

    public BankCard(String id, String cardHolderName, String panNumber, String expiratoryDate, BankAccount bankAccount) {
        this.id = id;
        this.cardHolderName = cardHolderName;
        this.panNumber = panNumber;
        this.expiratoryDate = expiratoryDate;
        this.bankAccount = bankAccount;
    }

    public BankCard(String cardHolderName, String panNumber, String expiratoryDate, BankAccount bankAccount) {
        this.cardHolderName = cardHolderName;
        this.panNumber = panNumber;
        this.expiratoryDate = expiratoryDate;
        this.bankAccount = bankAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getExpiratoryDate() {
        return expiratoryDate;
    }

    public void setExpiratoryDate(String expiratoryDate) {
        this.expiratoryDate = expiratoryDate;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
