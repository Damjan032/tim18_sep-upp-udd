package com.example.web_shopapi.controller;

import com.example.web_shopapi.dto.RoleDTO;
import com.example.web_shopapi.dto.UserDTO;
import com.example.web_shopapi.mapper.UserMapper;
import com.example.web_shopapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

	private final UserService userService;


	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(this.userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList()));
	}

	@GetMapping("/roles")
	public ResponseEntity<List<RoleDTO>> findAllRoles() {
		return ResponseEntity.ok(this.userService.findAllRoles().stream().map(RoleDTO::new).collect(Collectors.toList()));
	}


}
