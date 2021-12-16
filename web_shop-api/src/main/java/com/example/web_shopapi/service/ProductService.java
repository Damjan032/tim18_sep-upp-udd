package com.example.web_shopapi.service;

import com.example.web_shopapi.model.Product;
import com.example.web_shopapi.model.enums.LogMode;
import com.example.web_shopapi.model.enums.LogStatus;
import com.example.web_shopapi.repository.ProductRepository;
import com.example.web_shopapi.utils.Logger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Logger logger;
    public List<Product> findAll(){

        this.logger.writeLogs(LogMode.REGULAR, LogStatus.SUCCESS, "Find all products", InetAddress.getLoopbackAddress().getHostAddress());
        return productRepository.findAll();
    }
}
