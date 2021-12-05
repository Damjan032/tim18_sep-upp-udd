package com.example.web_shopapi.dto;

import com.example.web_shopapi.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {

	private long id;
	
	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	public RoleDTO(Role role) {
		this.id = role.getId();
		this.name = role.getName();
	}

}
