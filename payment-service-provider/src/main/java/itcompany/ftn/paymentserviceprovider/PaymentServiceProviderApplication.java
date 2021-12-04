package itcompany.ftn.paymentserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceProviderApplication.class, args);
	}

}
