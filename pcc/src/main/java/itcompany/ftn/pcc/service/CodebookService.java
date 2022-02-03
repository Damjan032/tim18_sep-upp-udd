package itcompany.ftn.pcc.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javassist.NotFoundException;

import itcompany.ftn.pcc.dto.PCCReqDTO;
import itcompany.ftn.pcc.dto.PCCResDTO;
import itcompany.ftn.pcc.model.Codebook;
import itcompany.ftn.pcc.repository.CodebookRepository;

@Service
public class CodebookService {
	
	@Autowired
	private CodebookRepository codeBookRepository;
	
	public PCCResDTO pay(@Valid PCCReqDTO pccRequestDto) throws NotFoundException {
		Codebook cb = codeBookRepository.findByIdentificationNumber(pccRequestDto.getPAN().substring(0, 6));

		if (cb == null) {
			//logger.trace("Nonexisting codebook");
			throw new NotFoundException("Invalid card data.");
		}

		RestTemplate rs = new RestTemplate();
		ResponseEntity<PCCResDTO> response = rs.postForEntity(cb.getUrl() + "/api/payment/pay", pccRequestDto,
				PCCResDTO.class);
		if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			//logger.trace("Invalid card data");
			throw new NotFoundException("Invalid credit card data.");
		}

		PCCResDTO pccResponse = response.getBody();

		return pccResponse;
	}

}
