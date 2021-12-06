package com.example.web_shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web_shopapi.model.Product;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long>{
    public List<Product> findProductsBySellerNotNull();
}
