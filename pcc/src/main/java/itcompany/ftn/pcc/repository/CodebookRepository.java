package itcompany.ftn.pcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itcompany.ftn.pcc.model.Codebook;

@Repository
public interface CodebookRepository extends JpaRepository<Codebook, Long> {
	
	Codebook findByIdentificationNumber(String substring);

}
