package itcompany.ftn.bitcoinpaymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAttributeDTO {

	@NotBlank(message = "Name is not provided.")
	private String name;

	@NotBlank(message = "Value is not provided.")
	private String value;
}
