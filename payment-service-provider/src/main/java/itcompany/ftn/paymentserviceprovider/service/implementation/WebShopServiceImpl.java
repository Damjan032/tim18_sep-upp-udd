package itcompany.ftn.paymentserviceprovider.service.implementation;

import itcompany.ftn.paymentserviceprovider.model.User;
import itcompany.ftn.paymentserviceprovider.model.WebShop;
import itcompany.ftn.paymentserviceprovider.model.enums.PaymentType;
import itcompany.ftn.paymentserviceprovider.repository.WebShopRepository;
import itcompany.ftn.paymentserviceprovider.service.UserService;
import itcompany.ftn.paymentserviceprovider.service.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebShopServiceImpl implements WebShopService {

    @Autowired
    WebShopRepository webShopRepository;

    @Autowired
    UserService userService;

    @Override
    public WebShop getById(String id) {
        if (id == null)
            return null;
        return webShopRepository.findById(id).orElse(null);
    }

    @Override
    public WebShop register(String name, String userId, String parentWebShopId, String successWebhook, String failureWebhook, String errorWebhook) {
        WebShop webShop = new WebShop();
        webShop.setName(name);
        webShop.setTransactionFailureWebhook(failureWebhook);
        webShop.setTransactionErrorWebhook(errorWebhook);

        User user = userService.getUserById(userId);
        webShop.setUser(user);

        WebShop parentWebShop = getById(parentWebShopId);
        if (parentWebShop != null) {
            webShop.setParentWebShop(parentWebShop);
            webShop = save(webShop);

            parentWebShop.getSubWebShops().add(webShop);
            user.setManagedWebShop(webShop);
            userService.saveUser(user);

            return save(parentWebShop);
        } else {
            webShop.setParentWebShop(null);
            webShop = save(webShop);

            user.setManagedWebShop(webShop);
            userService.saveUser(user);

            return webShop;
        }
    }

    @Override
    public WebShop save(WebShop store) {
        return webShopRepository.save(store);
    }

    @Override
    public void removePaymentType(String id, PaymentType paymentType) {
        WebShop webShop = getById(id);

        if (webShop != null) {
            webShop.getChosenPaymentTypes().remove(paymentType);
            save(webShop);
        }
    }

    @Override
    public void addPaymentType(String id, PaymentType paymentType) {
        WebShop webShop = getById(id);

        if (webShop != null) {
            webShop.getChosenPaymentTypes().add(paymentType);
            save(webShop);
        }
    }
}
