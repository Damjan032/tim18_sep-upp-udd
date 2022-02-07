package itcompany.ftn.bank2api.service;

import itcompany.ftn.bank2api.model.BankAccount;
import itcompany.ftn.bank2api.model.BankCard;

public interface BankAccountService {

    BankAccount save(BankAccount bankAccount);

    boolean validateMerchantInformation(String merchantId, String merchantPassword);

    void saveBankCard(BankCard bankCard);
}
