package itcompany.ftn.paymentserviceprovider.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class PayPalOrderDTO {

    private String invoiceId;
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String successURL;
    private String errorURL;
    private String failedURL;

}