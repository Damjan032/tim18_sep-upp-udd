package itcompany.ftn.bitcoinpaymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    BankAccount findByWebShopId(String webShopId);

}
