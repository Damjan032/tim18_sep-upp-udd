package itcompany.ftn.paymentserviceprovider.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaypalBtcTransactionDTO {
    private String invoiceId;
    private String type;
    private String status;
}
