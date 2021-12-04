package com.example.web_shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web_shopapi.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
