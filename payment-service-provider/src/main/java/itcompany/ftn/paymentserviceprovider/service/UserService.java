package itcompany.ftn.paymentserviceprovider.service;

import itcompany.ftn.paymentserviceprovider.model.User;

public interface UserService {

    User saveUser(User user);

    User registerUser(User user);

    User verifyUser(String id);

    User changePassword(String id, String password);

    User getUserById(String id);

    User getUserByEmail(String email);

    boolean isUserManagingWebShop(String id);
}
