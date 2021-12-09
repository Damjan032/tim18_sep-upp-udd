package itcompany.ftn.paymentserviceprovider.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class InvoiceItemDTO {
    private String id;

    @NotNull
    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    @NotBlank
    private String currency;

    public InvoiceItemDTO() {}

    public InvoiceItemDTO(String id, @NotNull @NotBlank String name, String description, @NotNull @Positive BigDecimal amount, @NotNull @NotBlank String currency) {
        this.id = id;
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
