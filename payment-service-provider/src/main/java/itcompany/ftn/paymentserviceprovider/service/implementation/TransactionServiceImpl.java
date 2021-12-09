package itcompany.ftn.paymentserviceprovider.service.implementation;

import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.Transaction;
import itcompany.ftn.paymentserviceprovider.repository.TransactionRepository;
import itcompany.ftn.paymentserviceprovider.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
