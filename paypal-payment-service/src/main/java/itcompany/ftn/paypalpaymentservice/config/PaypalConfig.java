package itcompany.ftn.paypalpaymentservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", "sandbox");
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(
                "AWnYrmb4ij6rPG7Xm48lPAoJyQEHUt37ZkWeTlHjxeMeDlg7IOErJbJKHslVJA2O8I0XE2XJcCRU7DfZ",
                "EF93lCp4gOGF79kNgNLbuW9DOploD2nyiIpjF6yF0JM_6ONIBQWb5wVJ1Hxinmaz5LUCMcUodaSJC3WX",
                paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }

}