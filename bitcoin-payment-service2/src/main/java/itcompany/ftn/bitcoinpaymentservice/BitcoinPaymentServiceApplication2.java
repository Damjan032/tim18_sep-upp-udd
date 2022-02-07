package itcompany.ftn.bitcoinpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BitcoinPaymentServiceApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinPaymentServiceApplication2.class, args);
	}

}
