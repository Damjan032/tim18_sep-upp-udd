package itcompany.ftn.pcc.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itcompany.ftn.pcc.dto.PCCReqDTO;
import itcompany.ftn.pcc.service.CodebookService;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

	@Autowired
	private CodebookService codeBookService;

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@PostMapping(value = "/pay")
	public ResponseEntity<?> pay(@Valid @RequestBody PCCReqDTO pccRequestDto) {
		logger.trace("Payment requested.");
		try {
			return ResponseEntity.ok(codeBookService.pay(pccRequestDto));
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

