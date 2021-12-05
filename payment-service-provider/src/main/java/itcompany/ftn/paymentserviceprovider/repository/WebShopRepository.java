package itcompany.ftn.paymentserviceprovider.repository;

import itcompany.ftn.paymentserviceprovider.model.WebShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebShopRepository extends JpaRepository<WebShop, String> {
}
