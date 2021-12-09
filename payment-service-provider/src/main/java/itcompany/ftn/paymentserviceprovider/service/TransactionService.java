package itcompany.ftn.paymentserviceprovider.service;

import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.Transaction;

public interface TransactionService {

    Transaction updateTransactionStatus(Invoice invoice, String status);
}
