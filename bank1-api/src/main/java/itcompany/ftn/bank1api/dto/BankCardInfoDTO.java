package itcompany.ftn.bank1api.dto;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BankCardInfoDTO {

    @NotNull
    @NotBlank
    @Size(min = 16, max = 16)
    @Pattern(regexp = "\\d{16}")
    @CreditCardNumber
    private String panNumber;

    @NotNull
    @NotBlank
    private String cardHolderName;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 5)
    @Pattern(regexp = "\\d{2}/\\d{2}")
    private String expiratoryDate;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 3)
    @Pattern(regexp = "\\d{3}")
    private String securityCode;

    public BankCardInfoDTO() {}

    public BankCardInfoDTO(@NotNull @NotBlank @Size(min = 16, max = 16) @Pattern(regexp = "\\d{16}") @CreditCardNumber String panNumber, @NotNull @NotBlank String cardHolderName, @NotNull @NotBlank @Size(min = 5, max = 5) @Pattern(regexp = "\\d{2}/\\d{2}") String expiratoryDate, @NotNull @NotBlank @Size(min = 3, max = 3) @Pattern(regexp = "\\d{3}") String securityCode) {
        this.panNumber = panNumber;
        this.cardHolderName = cardHolderName;
        this.expiratoryDate = expiratoryDate;
        this.securityCode = securityCode;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpiratoryDate() {
        return expiratoryDate;
    }

    public void setExpiratoryDate(String expiratoryDate) {
        this.expiratoryDate = expiratoryDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
