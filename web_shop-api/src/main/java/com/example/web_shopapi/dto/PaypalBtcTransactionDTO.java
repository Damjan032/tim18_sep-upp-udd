package com.example.web_shopapi.dto;
import lombok.Data;

@Data
public class PaypalBtcTransactionDTO {
    private String invoiceId;
    private String type;
    private String status;
}
