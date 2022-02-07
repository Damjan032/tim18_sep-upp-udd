package itcompany.ftn.bitcoinpaymentservice.service;

import itcompany.ftn.bitcoinpaymentservice.dto.BitcoinOrderRequestDTO;
import itcompany.ftn.bitcoinpaymentservice.dto.BitcoinOrderResponseDTO;
import itcompany.ftn.bitcoinpaymentservice.dto.BitcoinPaymentDTO;
import itcompany.ftn.bitcoinpaymentservice.enums.TransactionStatus;
import itcompany.ftn.bitcoinpaymentservice.exceptions.InvalidDataException;
import lombok.SneakyThrows;
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



	public String pay(BitcoinPaymentDTO pd) {
		String token = "KbbCDeaF24FgwzyPnm_w8KLj-U7ya-RxKRR8woPs";
		/*String token = pd.getAttributes().stream()
				.filter(attribute -> attribute.getName().equalsIgnoreCase("merchant token")).findFirst().get()
				.getValue();*/
 		System.out.println(callback);
		 System.out.println(sandbox);
		BitcoinOrderRequestDTO bitcoinOrder = BitcoinOrderRequestDTO.builder()
				.order_id(pd.getMerchantOrderId().toString()).price_amount(pd.getAmount()).price_currency("USD")
				.receive_currency("BTC").title("").description("").callback_url(callback)
				.cancel_url(pd.getFailedURL())
				.success_url(pd.getSuccessURL()).token(token).purchaser_email("mitarMiric123@mail.com")
				.build();

		String clientSecret = "Bearer " + token;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", clientSecret);

		ResponseEntity<BitcoinOrderResponseDTO> response = null;

		try {
			response = new RestTemplate().exchange(sandbox, HttpMethod.POST, new HttpEntity<>(bitcoinOrder, headers),
					BitcoinOrderResponseDTO.class);
		} catch (Exception e) {
			//logger.info("Create order CoinGate failed for payment with id " + pd.getMerchantOrderId());
			throw new InvalidDataException("Create order CoinGate failed", HttpStatus.BAD_REQUEST);
		}

		if (response.getBody() == null) {
			throw new InvalidDataException("Bitcoin service is not available", HttpStatus.BAD_GATEWAY);
		}

		String paymentUrl = response.getBody().getPayment_url();

		if (paymentUrl == null || paymentUrl.equals("")) {
			throw new InvalidDataException("Missing payment url from coingate", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return paymentUrl;
	}


	@Async
	@SneakyThrows
	public void sendTransactionResponse(Long transactionId, TransactionStatus status) {
		int statusInt = 0;
		if (status.equals(TransactionStatus.SUCCESS))
			statusInt = 1;
		if (status.equals(TransactionStatus.FAILED))
			statusInt = 2;
		if (status.equals(TransactionStatus.ERROR))
			statusInt = 3;
		HttpHeaders headers = new HttpHeaders();
		new RestTemplate().exchange("https://localhost:8089/api/transaction/" + transactionId + "?status=" + statusInt,
				HttpMethod.PUT, new HttpEntity<>(headers), Object.class);
	}

}
