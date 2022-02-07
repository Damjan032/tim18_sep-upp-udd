package itcompany.ftn.bank1api.repository;

import itcompany.ftn.bank1api.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankCardRepository extends JpaRepository<BankCard, String> {

    List<BankCard> findAllByCardHolderNameAndExpiratoryDate(String cardHolderName, String expiratoryDate);
    
    BankCard findByPanNumber(String panNumber);
    
}
