package com.example.web_shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web_shopapi.model.Order;

public interface OrderRepository  extends JpaRepository<Order, Long>{
	
}
