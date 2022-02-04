package itcompany.ftn.paymentserviceprovider.service;

import itcompany.ftn.paymentserviceprovider.dto.PaypalBtcTransactionDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.Transaction;
import itcompany.ftn.paymentserviceprovider.model.WebShop;

public interface TransactionService {

    Transaction updateTransactionStatus(Invoice invoice, String status);

    Transaction addBtcOrPPTransaction(Invoice invoice, WebShop webShop, PaypalBtcTransactionDTO paypalBtcTransactionDTO);
}
