package com.example.web_shopapi.controller;

import com.example.web_shopapi.dto.AuthTokenDTO;
import com.example.web_shopapi.dto.LoginDTO;
import com.example.web_shopapi.dto.TokenDTO;
import com.example.web_shopapi.mapper.UserMapper;
import com.example.web_shopapi.model.User;
import com.example.web_shopapi.model.enums.LogMode;
import com.example.web_shopapi.model.enums.LogStatus;
import com.example.web_shopapi.security.TokenUtils;
import com.example.web_shopapi.service.UserService;
import com.example.web_shopapi.utils.Logger;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {

	private final TokenUtils tokenUtils;
	private final AuthenticationManager authManager;
	private final UserMapper userMapper;
	private final Logger logger;

	@PostMapping("/login")
	public ResponseEntity<AuthTokenDTO> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
		try {
			User user = (User) this.authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())).getPrincipal();
			AuthTokenDTO token = new AuthTokenDTO(user, this.userMapper.getDto(user), this.tokenUtils.generateToken(user.getEmail()));
			this.logger.writeLogs(LogMode.REGULAR, LogStatus.SUCCESS, "LOGIN SUCCESS", InetAddress.getLoopbackAddress().getHostAddress());
			return ResponseEntity.ok(token);
		}
		catch (Exception e){
			this.logger.writeLogs(LogMode.REGULAR, LogStatus.ERROR, "LOGIN ERROR",  InetAddress.getLoopbackAddress().getHostAddress());
			return new ResponseEntity("Year of birth cannot be in the future", HttpStatus.BAD_REQUEST);
		}
	}


}
