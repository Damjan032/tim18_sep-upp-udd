package itcompany.ftn.bank2api.repository;

import itcompany.ftn.bank2api.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    BankAccount findByMerchantId(String merchantId);
}
