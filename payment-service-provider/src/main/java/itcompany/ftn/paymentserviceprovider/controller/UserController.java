package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.ChangePasswordDTO;
import itcompany.ftn.paymentserviceprovider.dto.UserRegistrationDTO;
import itcompany.ftn.paymentserviceprovider.model.User;
import itcompany.ftn.paymentserviceprovider.service.UserService;
import itcompany.ftn.paymentserviceprovider.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/payment-service-provider/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> webShopAdminRegistration(@RequestBody UserRegistrationDTO dto) {
        if (userService.getUserByEmail(dto.getEmail()) != null) {
            return new ResponseEntity<>("Email address is already in use", HttpStatus.BAD_REQUEST);
        }

        User user = EntityMapper.userRegistrationDTOtoUser(dto);
        userService.registerUser(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "verify/{id}", produces = "application/json")
    public ResponseEntity<Object> verifyUser(@PathVariable String id) {
        userService.verifyUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "change_password/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> verifyUser(@PathVariable String id, @RequestBody ChangePasswordDTO dto) {
        userService.changePassword(id, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
