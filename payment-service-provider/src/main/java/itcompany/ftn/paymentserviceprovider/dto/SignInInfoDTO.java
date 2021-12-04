package itcompany.ftn.paymentserviceprovider.dto;

import javax.validation.constraints.NotBlank;

public class SignInInfoDTO {

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public SignInInfoDTO() {}

    public SignInInfoDTO(@NotBlank(message = "Email cannot be empty") String email, @NotBlank(message = "Password cannot be empty") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
