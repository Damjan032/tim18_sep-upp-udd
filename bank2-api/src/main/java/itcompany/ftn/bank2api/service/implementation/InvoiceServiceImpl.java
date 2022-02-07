package itcompany.ftn.bank2api.service.implementation;

import itcompany.ftn.bank2api.dto.BankCardInvoiceDTO;
import itcompany.ftn.bank2api.dto.BankCardPaymentInfoDTO;
import itcompany.ftn.bank2api.model.BankAccount;
import itcompany.ftn.bank2api.model.Invoice;
import itcompany.ftn.bank2api.repository.BankAccountRepository;
import itcompany.ftn.bank2api.repository.InvoiceRepository;
import itcompany.ftn.bank2api.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Value("${uri.frontend}")
    private String frontendUri;

    @Override
    public BankCardPaymentInfoDTO getBankCardPaymentInfo(BankCardInvoiceDTO dto) {
        BankAccount merchantBankAccount = bankAccountRepository.findByMerchantId(dto.getMerchantId());

        Invoice invoice = new Invoice(merchantBankAccount, dto.getAmount(), dto.getCurrency(), dto.getMerchantOrderId(),
                dto.getMerchantTimestamp(), dto.getSuccessRedirectUrl(), dto.getFailureRedirectUrl(), dto.getErrorRedirectUrl());
        invoiceRepository.save(invoice);

        BankCardPaymentInfoDTO bankCardPaymentInfoDTO = new BankCardPaymentInfoDTO();
        bankCardPaymentInfoDTO.setPaymentUrl(String.format("%s/payment/%s", frontendUri, invoice.getId()));
        bankCardPaymentInfoDTO.setPaymentId(invoice.getId());

        return bankCardPaymentInfoDTO;
    }

    @Override
    public Invoice getById(String invoiceId) {
        return invoiceRepository.findById(invoiceId).orElse(null);
    }
}
