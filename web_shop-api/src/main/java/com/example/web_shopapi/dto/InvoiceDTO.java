package com.example.web_shopapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public class InvoiceDTO {

    private String id;

    @NotNull
    @NotBlank
    private String webShopId;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    @NotBlank
    private String currency;

    @NotNull
    @NotEmpty
    private List<InvoiceItemDTO> invoiceItems;

    public InvoiceDTO() {}

    public InvoiceDTO(String id, @NotNull @NotBlank String webShopId, @NotNull @Positive BigDecimal amount, @NotNull @NotBlank String currency, @NotNull @NotEmpty List<InvoiceItemDTO> invoiceItems) {
        this.id = id;
        this.webShopId = webShopId;
        this.amount = amount;
        this.currency = currency;
        this.invoiceItems = invoiceItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebShopId() {
        return webShopId;
    }

    public void setWebShopId(String webShopId) {
        this.webShopId = webShopId;
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

    public List<InvoiceItemDTO> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemDTO> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}