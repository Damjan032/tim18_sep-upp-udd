package itcompany.ftn.paymentserviceprovider.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePasswordDTO {

    @NotNull
    @NotBlank
    private String password;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(@NotNull @NotBlank String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}