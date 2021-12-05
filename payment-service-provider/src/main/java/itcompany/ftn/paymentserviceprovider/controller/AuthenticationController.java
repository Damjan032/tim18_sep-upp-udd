package itcompany.ftn.paymentserviceprovider.controller;

import itcompany.ftn.paymentserviceprovider.dto.SignInInfoDTO;
import itcompany.ftn.paymentserviceprovider.model.User;
import itcompany.ftn.paymentserviceprovider.security.TokenUtils;
import itcompany.ftn.paymentserviceprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/payment-service-provider/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    TokenUtils tokenUtils;

    @Validated
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> authentication(@Valid @RequestBody SignInInfoDTO loginInfo){
        try {
            String token = tryToAuthenticate(loginInfo);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid user email or password", HttpStatus.BAD_REQUEST);
        }
    }

    private String tryToAuthenticate(SignInInfoDTO loginInfo) {
        User user = userService.getUserByEmail(loginInfo.getEmail());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getId(), loginInfo.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        UserDetails details = userDetailsService.loadUserByUsername(user.getId());
        return tokenUtils.generateToken(details, user.getRole() );
    }
}
