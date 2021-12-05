package com.example.web_shopapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TokenDTO {
	
	@NotBlank(message = "Token cannot be blank")
	private String token;
	
}
