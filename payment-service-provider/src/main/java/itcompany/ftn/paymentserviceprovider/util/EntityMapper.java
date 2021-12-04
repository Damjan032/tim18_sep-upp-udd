package itcompany.ftn.paymentserviceprovider.util;

import itcompany.ftn.paymentserviceprovider.dto.UserRegistrationDTO;
import itcompany.ftn.paymentserviceprovider.model.User;
import itcompany.ftn.paymentserviceprovider.model.enums.Role;

public class EntityMapper {

    public static User userRegistrationDTOtoUser(UserRegistrationDTO dto) {

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setActive(true);
        user.setRequiresPasswordChange(false);
        user.setRole(Role.ROLE_WEB_SHOP_ADMIN);
        return user;
    }
}
