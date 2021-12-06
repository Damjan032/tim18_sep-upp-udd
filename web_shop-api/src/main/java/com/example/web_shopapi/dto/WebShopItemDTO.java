package com.example.web_shopapi.dto;

import com.example.web_shopapi.model.Course;
import com.example.web_shopapi.model.Product;
import lombok.Data;

@Data
public class WebShopItemDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private UserDTO seller;
    private WebShopItemType type;


    public WebShopItemDTO(Product product){

        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.seller = new UserDTO(product.getSeller());
        this.type = WebShopItemType.PRODUCT;
    }

    public WebShopItemDTO(Course course){

        this.id = course.getId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.price = course.getPrice();
        this.seller = new UserDTO(course.getSeller());
        this.type = WebShopItemType.COURSE;
    }
}
