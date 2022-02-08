package itcompany.ftn.bitcoinpaymentservice.service;

import itcompany.ftn.bitcoinpaymentservice.dto.BitcoinOrderRequestDTO;
import itcompany.ftn.bitcoinpaymentservice.dto.BitcoinOrderResponseDTO;
import itcompany.ftn.bitcoinpaymentservice.dto.BitcoinPaymentDTO;
import itcompany.ftn.bitcoinpaymentservice.enums.TransactionStatus;
import itcompany.ftn.bitcoinpaymentservice.exceptions.InvalidDataException;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

	@Value("${CALLBACK_URL}")
	private String callback;

	@Value("${SANDBOX_URL}")
	private String sandbox;

	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);


	public String pay(BitcoinPaymentDTO bitcoinPaymentDTO) {
		logger.info("Payment requested for payment with id " + bitcoinPaymentDTO.getMerchantOrderId());
		BitcoinOrderRequestDTO bitcoinOrder = BitcoinOrderRequestDTO.builder()
				.order_id(bitcoinPaymentDTO.getMerchantOrderId().toString()).price_amount(bitcoinPaymentDTO.getAmount()).price_currency("USD")
				.receive_currency("BTC").title("").description("").callback_url(callback)
				.cancel_url(bitcoinPaymentDTO.getFailedURL()+ "&invoceId=" + bitcoinPaymentDTO.getMerchantOrderId() + "&type=BITCOIN&")
				.success_url(bitcoinPaymentDTO.getSuccessURL()+ "&invoceId=" + bitcoinPaymentDTO.getMerchantOrderId() + "&type=BITCOIN&").purchaser_email("").
				token(bitcoinPaymentDTO.getToken())
				.build();

		String clientSecret = "Bearer " + bitcoinOrder.getToken();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", clientSecret);

		ResponseEntity<BitcoinOrderResponseDTO> response = null;

		try {
			response = new RestTemplate().exchange(sandbox, HttpMethod.POST, new HttpEntity<>(bitcoinOrder, headers),
					BitcoinOrderResponseDTO.class);
		} catch (Exception e) {
			logger.error("Create order CoinGate failed for payment with id " + bitcoinPaymentDTO.getMerchantOrderId());
			throw new InvalidDataException("Create order CoinGate failed", HttpStatus.BAD_REQUEST);
		}

		if (response.getBody() == null) {
			logger.error("Bitcoin service is not available");
			throw new InvalidDataException("Bitcoin service is not available", HttpStatus.BAD_GATEWAY);
		}

		String paymentUrl = response.getBody().getPayment_url();

		if (paymentUrl == null || paymentUrl.equals("")) {
			logger.error("Missing payment url from CoinGate");
			throw new InvalidDataException("Missing payment url from CoinGate", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return paymentUrl;
	}

}
