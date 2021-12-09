package itcompany.ftn.bank1api.service.implementation;

import itcompany.ftn.bank1api.model.BankAccount;
import itcompany.ftn.bank1api.repository.BankAccountRepository;
import itcompany.ftn.bank1api.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public boolean validateMerchantInformation(String merchantId, String merchantPassword) {
        BankAccount bankAccount = bankAccountRepository.findByMerchantId(merchantId);
        if (bankAccount == null)
            return false;

        //TODO: return passwordEncoder.matches(merchantPassword, bankAccount.getMerchantPassword());
        return  merchantPassword.equals(bankAccount.getMerchantPassword());
    }
}
