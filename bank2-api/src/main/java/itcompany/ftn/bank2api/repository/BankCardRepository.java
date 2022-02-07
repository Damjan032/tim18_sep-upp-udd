package itcompany.ftn.bank2api.repository;

import itcompany.ftn.bank2api.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankCardRepository extends JpaRepository<BankCard, String> {

    List<BankCard> findAllByCardHolderNameAndExpiratoryDate(String cardHolderName, String expiratoryDate);
    
    BankCard findByPanNumber(String panNumber);
    
}
