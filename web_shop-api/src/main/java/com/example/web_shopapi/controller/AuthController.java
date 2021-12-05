package com.example.web_shopapi.controller;

import com.example.web_shopapi.dto.AuthTokenDTO;
import com.example.web_shopapi.dto.LoginDTO;
import com.example.web_shopapi.dto.TokenDTO;
import com.example.web_shopapi.mapper.UserMapper;
import com.example.web_shopapi.model.User;
import com.example.web_shopapi.security.TokenUtils;
import com.example.web_shopapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {

	private final TokenUtils tokenUtils;
	private final AuthenticationManager authManager;
	private final UserMapper userMapper;

	
	@PostMapping("/login")
	public ResponseEntity<AuthTokenDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
		User user = (User) this.authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())).getPrincipal();
		AuthTokenDTO token = new AuthTokenDTO(user, this.userMapper.getDto(user), this.tokenUtils.generateToken(user.getEmail()));
		return ResponseEntity.ok(token);
	}


}
