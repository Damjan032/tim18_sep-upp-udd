package com.example.web_shopapi.mapper;


import com.example.web_shopapi.dto.RoleDTO;
import com.example.web_shopapi.dto.UserDTO;
import com.example.web_shopapi.model.Role;
import com.example.web_shopapi.model.User;
import com.example.web_shopapi.repository.RoleRepository;
import com.example.web_shopapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
	
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	
	@Transactional(readOnly = true)
    public User map(UserDTO userDTO) {
    	User user = new User();
    	this.setModel(user, userDTO);
        return user;
    }
    
	@Transactional(readOnly = true)
    public User map(long id, UserDTO userDTO) {
    	User user = this.userRepository.findById(userDTO.getId()).get();
    	this.setModel(user, userDTO);
        return user;
    }
    
    private void setModel(User user, UserDTO userDTO) {
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        List<Role> roles = this.roleRepository.findAllById(userDTO.getRoles().stream().map(RoleDTO::getId).collect(Collectors.toList()));
        user.setRoles(new HashSet<>(roles));
    }
    
    @Transactional(readOnly = true)
    public UserDTO getDto(User user){
	    UserDTO userDTO = new UserDTO();
	    userDTO.setEmail(user.getEmail());
	    userDTO.setFirstName(user.getFirstName());
	    userDTO.setLastName(user.getLastName());
	    return userDTO;
    }
    
    @Transactional(readOnly = true)
    public User map(long id, UserDTO userDTO, boolean enabled) {
        User user = this.userRepository.findById(userDTO.getId()).get();
        this.setModel(user, userDTO);
        return user;
    }
}
