package itcompany.ftn.paymentserviceprovider.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class BtcTokenDTO {
    @NonNull
    private String invoiceId;
    @NonNull
    private String token;
}
