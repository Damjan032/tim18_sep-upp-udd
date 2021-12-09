package itcompany.ftn.bank1api.service;

import itcompany.ftn.bank1api.model.BankAccount;

public interface BankAccountService {

    BankAccount save(BankAccount bankAccount);

    boolean validateMerchantInformation(String merchantId, String merchantPassword);
}
