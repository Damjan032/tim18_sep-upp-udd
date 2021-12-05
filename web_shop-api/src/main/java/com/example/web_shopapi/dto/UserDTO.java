package com.example.web_shopapi.dto;

import com.example.web_shopapi.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^\\D*$", message = "Numbers not allowed in first name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^\\D*$", message = "Numbers not allowed in last name")
    private String lastName;

	@NotNull(message = "Roles cannot be null")
    private List<RoleDTO> roles;

    
    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles().stream().map(RoleDTO::new).collect(Collectors.toList());
    }

}
