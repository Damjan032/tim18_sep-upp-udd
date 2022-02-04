package itcompany.ftn.paymentserviceprovider.service.implementation;

import itcompany.ftn.paymentserviceprovider.dto.PaypalBtcTransactionDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.Transaction;
import itcompany.ftn.paymentserviceprovider.model.WebShop;
import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;
import itcompany.ftn.paymentserviceprovider.repository.TransactionRepository;
import itcompany.ftn.paymentserviceprovider.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public Transaction updateTransactionStatus(Invoice invoice, String status) {
        Transaction transaction = invoice.getCustomerTransaction();
        transaction.setStatus(status);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction addBtcOrPPTransaction(Invoice invoice, WebShop webShop, PaypalBtcTransactionDTO paypalBtcTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setStatus(paypalBtcTransactionDTO.getStatus());
        transaction.setUtcTransactionTime(new Date());
        transaction.setInvoice(invoice);
        transaction.setAmount(invoice.getAmount());
        transaction.setType(
                (paypalBtcTransactionDTO.getType().toLowerCase(Locale.ROOT).equals("paypal"))
                ? PaymentType.PAYPAL: PaymentType.BITCOIN);
        transaction.setWebShop(webShop);
        transaction.setUrl("google.com");
        return transactionRepository.save(transaction);
    }
}
