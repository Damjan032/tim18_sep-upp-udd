package itcompany.ftn.paymentserviceprovider.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BitcoinPaymentDTO {

	@NotNull(message = "Merchant id is not provided.")
	private Long merchantOrderId;

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
