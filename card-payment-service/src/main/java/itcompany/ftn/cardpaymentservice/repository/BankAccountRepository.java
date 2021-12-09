package itcompany.ftn.cardpaymentservice.repository;

import itcompany.ftn.cardpaymentservice.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    BankAccount findByWebShopId(String webShopId);

}
