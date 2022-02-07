package itcompany.ftn.bank1api.service.implementation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itcompany.ftn.bank1api.dto.BankCardInfoDTO;
import itcompany.ftn.bank1api.dto.BankCardPaymentResponseDTO;
import itcompany.ftn.bank1api.dto.PCCReqDTO;
import itcompany.ftn.bank1api.dto.PCCResDTO;
import itcompany.ftn.bank1api.model.BankAccount;
import itcompany.ftn.bank1api.model.BankCard;
import itcompany.ftn.bank1api.model.Invoice;
import itcompany.ftn.bank1api.model.Transaction;
import itcompany.ftn.bank1api.model.TransactionStatus;
import itcompany.ftn.bank1api.repository.BankAccountRepository;
import itcompany.ftn.bank1api.repository.BankCardRepository;
import itcompany.ftn.bank1api.repository.InvoiceRepository;
import itcompany.ftn.bank1api.repository.TransactionRepository;
import itcompany.ftn.bank1api.service.PaymentService;

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
    public boolean payInvoice(Invoice invoice, BankCardInfoDTO bankCardInfoDTO) {
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

        if (buyerBankCard != null) { // same bank
            System.out.println("banke prodavca i kupca su iste ");
            return payFromSameBank(invoice, buyerBankCard);
        }
        // TODO: else - different banks
        System.out.println("banke prodavca i kupca su razlicite ");
        return payFromDifferentBank(invoice,bankCardInfoDTO);
    }

    private boolean payFromDifferentBank(Invoice invoice, BankCardInfoDTO bankCardInfoDTO) {
        Transaction transaction = new Transaction(bankCardInfoDTO.getCardHolderName() , invoice.getMerchantBankAccount().getMerchantId(), invoice.getAmount(), invoice.getCurrency());
		
        transactionRepository.save(transaction);
        System.out.println("transakcija sacuvana");
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(d);
		
		PCCReqDTO pccRequestDto = new PCCReqDTO(transaction.getId(), Timestamp.valueOf(dateStr),
				invoice.getAmount(), bankCardInfoDTO.getPanNumber(), bankCardInfoDTO.getCardHolderName(), bankCardInfoDTO.getExpiratoryDate(),
				bankCardInfoDTO.getSecurityCode());

		RestTemplate rs = new RestTemplate();
		System.out.println("salje se zahtev pccu");
		ResponseEntity<PCCResDTO> response = rs.postForEntity("http://localhost:8003/api/payment/pay",
				pccRequestDto, PCCResDTO.class);
		if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			//logger.debug("Invalid credit card data");
			return false;
		}

		PCCResDTO pccResponse = response.getBody();
		transaction.setStatus(pccResponse.getStatus());
		transactionRepository.save(transaction);

		if (transaction.getStatus().equals(TransactionStatus.SUCCESS)) {
			//logger.info("Successfull transaction with id " + transaction.getId());
			// obavi transakciju
	        BankAccount merchantBankAccount = invoice.getMerchantBankAccount();
	        merchantBankAccount.setBalance(merchantBankAccount.getBalance().add(invoice.getAmount())); // TODO: exchange currency
            bankAccountRepository.save(merchantBankAccount);
			return true;
		}
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
    public void notifySuccess(Invoice invoice) {
        BankCardPaymentResponseDTO responseDTO = new BankCardPaymentResponseDTO(
                "success", invoice.getMerchantOrderId(), invoice.getId(), null, null
                );
        pspRestTemplate.postForEntity(invoice.getSuccessRedirectUrl(), responseDTO, String.class);
    }

    @Override
    public void notifyClientException(Invoice invoice) {
        // TODO: Write meaningful message
        BankCardPaymentResponseDTO responseDTO = new BankCardPaymentResponseDTO(
                "Invalid pan or not enough funds", invoice.getMerchantOrderId(), invoice.getId(), null, null
        );
        pspRestTemplate.postForEntity(invoice.getFailureRedirectUrl(), responseDTO, String.class);
    }

    @Override
    public void notifyServerException(Invoice invoice) {
    // TODO: Write meaningful message
        BankCardPaymentResponseDTO responseDTO = new BankCardPaymentResponseDTO(
                "Some exception happened on server side", invoice.getMerchantOrderId(), invoice.getId(), null, null
        );
        pspRestTemplate.postForEntity(invoice.getErrorRedirectUrl(), responseDTO, String.class);
    }


}
