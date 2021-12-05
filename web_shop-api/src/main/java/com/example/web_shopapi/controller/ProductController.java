package com.example.web_shopapi.controller;

import com.example.web_shopapi.dto.*;
import com.example.web_shopapi.service.CourseService;
import com.example.web_shopapi.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/products", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<WebShopItemsDTO> findAll() {
        return ResponseEntity.ok(new WebShopItemsDTO(
                this.productService.findAll().stream().map(ProductDTO::new).collect(Collectors.toList()),
                this.courseService.findAll().stream().map(CourseDTO::new).collect(Collectors.toList())));
    }
}
