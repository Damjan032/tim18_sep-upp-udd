package itcompany.ftn.bank2api.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PCCReqDTO {

	@NotBlank(message = "Acquirer order id is not provided.")
	private String acquirerOrderId;

	@NotNull(message = "Acquirer timestamp is not provided.")
	private Timestamp acquirerTimestamp;

	@NotNull(message = "Amount is not provided.")
	private BigDecimal amount;

	@NotBlank(message = "PAN number is empty.")
    @Pattern(regexp = "^[0-9]{16}$")
    private String PAN;

    @NotBlank(message = "Card Holder Name is empty.")
    @Pattern(regexp = "^(([A-Za-zÀ-ƒ]+[.]?[ ]?|[a-zÀ-ƒ]+['-]?){0,4})$")
    private String cardHolderName;

    @NotBlank(message = "Expiration date is empty")
    @Pattern(regexp = "^([01]?[0-9]?(\\/)[0-9]{2,4})$")
    private String expirationDate;

    @NotBlank(message = "Security Code is empty.")
    @Pattern(regexp = "^[0-9]{3,4}$")
    private String securityCode;
    
	public PCCReqDTO() {
		super();
	}

	public PCCReqDTO(String acquirerOrderId, Timestamp acquirerTimestamp, BigDecimal amount, String pAN,
			String cardHolderName, String expirationDate, String securityCode) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.amount = amount;
		PAN = pAN;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}

	public String getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(String acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Timestamp getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Timestamp acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	    
}
