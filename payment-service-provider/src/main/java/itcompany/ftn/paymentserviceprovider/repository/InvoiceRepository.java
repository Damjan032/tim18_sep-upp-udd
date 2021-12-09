package itcompany.ftn.paymentserviceprovider.repository;

import itcompany.ftn.paymentserviceprovider.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
