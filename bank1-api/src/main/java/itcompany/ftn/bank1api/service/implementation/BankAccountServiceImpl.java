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
}
