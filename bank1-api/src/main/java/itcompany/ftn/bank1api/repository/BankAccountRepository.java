package itcompany.ftn.bank1api.repository;

import itcompany.ftn.bank1api.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    BankAccount findByMerchantId(String merchantId);
}
