package itcompany.ftn.paymentserviceprovider.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BitcoinPaymentDTO {

	@NotNull(message = "Merchant id is not provided.")
	private String merchantOrderId;

	@NotNull
	private Timestamp merchantTimestamp;

	@NotNull(message = "Amount is not provided.")
	private Double amount;

	private String token;

	@NotBlank(message = "Success url is not provided.")
	private String successURL;

	@NotBlank(message = "Error url is not provided.")
	private String errorURL;

	@NotBlank(message = "Failed url is not provided.")
	private String failedURL;
}
