package itcompany.ftn.bank2api.dto;

import java.time.LocalDateTime;

public class BankCardPaymentResponseDTO {
    private String paymentId;

    private String transactionStatus;

    private String merchantOrderId;

    private String acquirerOrderId;

    private LocalDateTime acquirerTimestamp;

    public BankCardPaymentResponseDTO() {
    }

    public BankCardPaymentResponseDTO(String paymentId, String transactionStatus, String merchantOrderId, String acquirerOrderId, LocalDateTime acquirerTimestamp) {
        this.paymentId = paymentId;
        this.transactionStatus = transactionStatus;
        this.merchantOrderId = merchantOrderId;
        this.acquirerOrderId = acquirerOrderId;
        this.acquirerTimestamp = acquirerTimestamp;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getAcquirerOrderId() {
        return acquirerOrderId;
    }

    public void setAcquirerOrderId(String acquirerOrderId) {
        this.acquirerOrderId = acquirerOrderId;
    }

    public LocalDateTime getAcquirerTimestamp() {
        return acquirerTimestamp;
    }

    public void setAcquirerTimestamp(LocalDateTime acquirerTimestamp) {
        this.acquirerTimestamp = acquirerTimestamp;
    }
}
