package com.example.web_shopapi.dto;

import com.example.web_shopapi.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AuthTokenDTO {

	private long id;
	private String token;
	private List<RoleDTO> roles;
	private List<String> authorities;
	private UserDTO user;
	
	public AuthTokenDTO(User user, UserDTO userDto, String token) {
		super();
		this.id = user.getId();
		this.token = token;
		this.roles = user.getRoles().stream().map(RoleDTO::new).collect(Collectors.toList());
		this.authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		this.user = userDto;
	}
}
