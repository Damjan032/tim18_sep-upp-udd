package itcompany.ftn.bank1api.service.implementation;

import itcompany.ftn.bank1api.dto.BankCardInfoDTO;
import itcompany.ftn.bank1api.dto.BankCardPaymentResponseDTO;
import itcompany.ftn.bank1api.model.BankAccount;
import itcompany.ftn.bank1api.model.BankCard;
import itcompany.ftn.bank1api.model.Invoice;
import itcompany.ftn.bank1api.model.Transaction;
import itcompany.ftn.bank1api.repository.BankAccountRepository;
import itcompany.ftn.bank1api.repository.BankCardRepository;
import itcompany.ftn.bank1api.repository.InvoiceRepository;
import itcompany.ftn.bank1api.repository.TransactionRepository;
import itcompany.ftn.bank1api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    BankCardRepository bankCardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Bean("pspRestTemplate")
    public RestTemplate pspRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    @Qualifier("pspRestTemplate")
    private RestTemplate pspRestTemplate;

    @Override
    public boolean payInvoice(String invoiceId, BankCardInfoDTO bankCardInfoDTO) {
        Invoice invoice = invoiceRepository.getById(invoiceId);
        if (invoice == null)
            return  false;

        if (invoice.getTransaction() != null) // already paid
            return true;

        // TODO: verify bank card data

        BankCard buyerBankCard = null;
        List<BankCard> bankCards = bankCardRepository.findAllByCardHolderNameAndExpiratoryDate(bankCardInfoDTO.getCardHolderName(), bankCardInfoDTO.getExpiratoryDate());
        for (BankCard card : bankCards) {
            // TODO: if (encoder.matches(bankCardInfoDTO.getPanNumber(), card.getPanNumber()))
            if (bankCardInfoDTO.getPanNumber().equals(card.getPanNumber())) {
                buyerBankCard = card;
                break;
            }
        }

        if (buyerBankCard != null) // same bank
            return payFromSameBank(invoice, buyerBankCard);
        // TODO: else - different banks

        return false;
    }

    private boolean payFromSameBank(Invoice invoice, BankCard buyerBankCard) {
        BankAccount buyerBankAccount = buyerBankCard.getBankAccount();
        BankAccount merchantBankAccount = invoice.getMerchantBankAccount();

        // TODO: exchange currency

        if (buyerBankAccount.getBalance().compareTo(invoice.getAmount()) > 0) {
            Transaction transaction = new Transaction(buyerBankAccount.getId(), merchantBankAccount.getMerchantId(), invoice.getAmount(), invoice.getCurrency());
            transaction = transactionRepository.save(transaction);

            invoice.setTransaction(transaction);
            invoice = invoiceRepository.save(invoice);

            buyerBankAccount.setBalance(buyerBankAccount.getBalance().subtract(invoice.getAmount())); // TODO: exchange currency
            bankAccountRepository.save(buyerBankAccount);

            merchantBankAccount.setBalance(merchantBankAccount.getBalance().add(invoice.getAmount())); // TODO: exchange currency
            bankAccountRepository.save(merchantBankAccount);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void notifyClientException(String invoiceId) {
        Invoice invoice = invoiceRepository.getById(invoiceId);
        BankCardPaymentResponseDTO responseDTO = new BankCardPaymentResponseDTO(
                "success", invoice.getMerchantOrderId(), invoice.getId(), invoice.getTransaction().getId(), invoice.getTransaction().getTimestamp()
                );
        pspRestTemplate.postForEntity(invoice.getSuccessRedirectUrl(), responseDTO, String.class);
    }

    @Override
    public void notifySuccess(String invoiceId) {
        // TODO: Write meaningful message
        Invoice invoice = invoiceRepository.getById(invoiceId);
        BankCardPaymentResponseDTO responseDTO = new BankCardPaymentResponseDTO(
                "Invalid pan or not enough funds", invoice.getMerchantOrderId(), invoice.getId(), invoice.getTransaction().getId(), invoice.getTransaction().getTimestamp()
        );
        pspRestTemplate.postForEntity(invoice.getFailureRedirectUrl(), responseDTO, String.class);
    }

    @Override
    public void notifyServerException(String invoiceId) {
// TODO: Write meaningful message
        Invoice invoice = invoiceRepository.getById(invoiceId);
        BankCardPaymentResponseDTO responseDTO = new BankCardPaymentResponseDTO(
                "Some exception happened on server side", invoice.getMerchantOrderId(), invoice.getId(), invoice.getTransaction().getId(), invoice.getTransaction().getTimestamp()
        );
        pspRestTemplate.postForEntity(invoice.getErrorRedirectUrl(), responseDTO, String.class);
    }


}
