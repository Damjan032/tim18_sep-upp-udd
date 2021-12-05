package com.example.web_shopapi.service;

import com.example.web_shopapi.model.Role;
import com.example.web_shopapi.model.User;
import com.example.web_shopapi.repository.RoleRepository;
import com.example.web_shopapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username);
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        try {
            Page<User> response = this.userRepository.findAll(pageable);
            logger.info("Users page number {} successfully fetched.", pageable.getPageNumber());
            return response;
        } catch (Exception e) {
            logger.error("Error occured while fetching users page number {}.", pageable.getPageNumber());
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        try {
            List<User> response = this.userRepository.findAll();
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Role> findAllRoles() {
        try {
            List<Role> response = this.roleRepository.findAll();
            logger.info("Roles successfully fetched");
            return response;
        } catch (Exception e) {
            logger.error("Error occurred while fetching roles, message: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional(readOnly = false)
    public User save(User user) {
        try {
            User response = this.userRepository.save(user);
            logger.info("User with id {} successfully saved.", response.getId());
            return response;
        } catch (Exception e) {
            logger.error("Error occurred while saving user, message: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional(readOnly = false)
    public void delete(long id) {
        try {
            this.userRepository.deleteById(id);
            logger.info("User with id {} successfully deleted.", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting user with id {}, message: {}", id, e.getMessage());
            throw e;
        }
    }

}
