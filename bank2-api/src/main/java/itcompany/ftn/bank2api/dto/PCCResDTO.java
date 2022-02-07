package itcompany.ftn.bank2api.dto;

import java.sql.Timestamp;
import itcompany.ftn.bank2api.model.TransactionStatus;



public class PCCResDTO {

	private String acquirerOrderId;
	private String issuerOrderId;
	private Timestamp acquirerTimestamp;
	private Timestamp issuerTimestamp;
	private TransactionStatus status;
	
	public PCCResDTO() {
		super();
	}

	public PCCResDTO(String acquirerOrderId, String issuerOrderId, Timestamp acquirerTimestamp, Timestamp issuerTimestamp,
			TransactionStatus status) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.issuerOrderId = issuerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.issuerTimestamp = issuerTimestamp;
		this.status = status;
	}

	public String getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(String acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public String getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(String issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Timestamp getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Timestamp acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public Timestamp getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(Timestamp issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
		
}
