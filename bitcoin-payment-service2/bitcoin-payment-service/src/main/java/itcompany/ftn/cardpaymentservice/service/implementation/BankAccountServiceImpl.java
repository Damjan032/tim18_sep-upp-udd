package itcompany.ftn.bitcoinpaymentservice.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Value("${bank-uris.bank1}")
    private String bank1Api;

    @Value("${bank-uris.bank2}")
    private String bank2Api;

    @Override
    public void delete(String webShopId) {
        BankAccount bankAccount = bankAccountRepository.findByWebShopId(webShopId);
        if (bankAccount != null)
            bankAccountRepository.deleteById(webShopId);
    }

    @Override
    public boolean save(String webShopId, String merchantId, String merchantPassword, String bankName) {
        String bankUri;
        if (bankName.equalsIgnoreCase("bank1"))
            bankUri = String.format("%s/invoice", bank1Api);
        else if (bankName.equalsIgnoreCase("bank2"))
            bankUri = String.format("%s/invoice", bank2Api);
        else
            return false;

        BankAccount bankAccount = new BankAccount(webShopId, merchantId, merchantPassword, bankUri);
        bankAccountRepository.save(bankAccount);
        return  true;
    }
}
