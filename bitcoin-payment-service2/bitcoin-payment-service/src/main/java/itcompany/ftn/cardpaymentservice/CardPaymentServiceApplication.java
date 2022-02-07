package itcompany.ftn.bitcoinpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CardPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardPaymentServiceApplication.class, args);
	}

}
