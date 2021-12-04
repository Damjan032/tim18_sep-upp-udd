package com.example.web_shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.web_shopapi.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
	
}

