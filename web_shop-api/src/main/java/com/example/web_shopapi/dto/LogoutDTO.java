package com.example.web_shopapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LogoutDTO {
    @NotNull(message = "Token cannot be empty")
    String token;
}
