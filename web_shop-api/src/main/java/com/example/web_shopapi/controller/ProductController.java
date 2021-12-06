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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "api/products", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<WebShopItemDTO>> findAll() {
        return ResponseEntity.ok(
                Stream.of(this.productService.findAll().stream().map(WebShopItemDTO::new).collect(Collectors.toList()),
                                this.courseService.findAll().stream().map(WebShopItemDTO::new).collect(Collectors.toList()))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList()));
    }
}
