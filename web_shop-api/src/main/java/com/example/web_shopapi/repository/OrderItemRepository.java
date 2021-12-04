package com.example.web_shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web_shopapi.model.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Long>{

}
