package itcompany.ftn.paymentserviceprovider.service.implementation;

import itcompany.ftn.paymentserviceprovider.dto.CardPaymentTypeDTO;
import itcompany.ftn.paymentserviceprovider.model.User;
import itcompany.ftn.paymentserviceprovider.model.WebShop;
import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;
import itcompany.ftn.paymentserviceprovider.service.PaymentTypeService;
import itcompany.ftn.paymentserviceprovider.service.UserService;
import itcompany.ftn.paymentserviceprovider.service.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    WebShopService webShopService;

    @Autowired
    UserService userService;

    @Value("${gateway-service-uri}")
    String gatewayServiceUri;

    @LoadBalanced
    @Bean
    public RestTemplate paymentRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    @Qualifier("paymentRestTemplate")
    private RestTemplate paymentRestTemplate;

    @Override
    public boolean addPaymentTypeViaCard(String userId, String bankName, String merchantId, String merchantPassword) {
        User user = userService.getUserById(userId);
        if (user == null)
            return false;

        WebShop webShop = user.getManagedWebShop();
        if (webShop == null)
            return false;

        if (webShop.getChosenPaymentTypes().contains(PaymentType.CARD))
            return true;

        CardPaymentTypeDTO cardPaymentTypeDTO = new CardPaymentTypeDTO(webShop.getId(), bankName, merchantId, merchantPassword);
        ResponseEntity responseEntity = paymentRestTemplate.exchange(
                String.format("%s/api/card-payment-service/account", gatewayServiceUri),
                HttpMethod.POST, new HttpEntity<>(cardPaymentTypeDTO), ResponseEntity.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            webShopService.addPaymentType(webShop.getId(), PaymentType.CARD);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removePaymentType(String userId, PaymentType paymentType) {
        User user = userService.getUserById(userId);
        if (user == null)
            return false;

        WebShop webShop = user.getManagedWebShop();
        if (webShop == null)
            return false;

        ResponseEntity responseEntity =  paymentRestTemplate.exchange(
                String.format("%s/api/%s-payment-service/account/%s", gatewayServiceUri, paymentType.toString().toLowerCase(), webShop.getId()),
                HttpMethod.DELETE, null, ResponseEntity.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            webShopService.removePaymentType(webShop.getId(), paymentType);
            return true;
        }
        else {
            return false;
        }
    }
}
