package com.example.web_shopapi.dto;

import com.example.web_shopapi.model.Product;
import com.example.web_shopapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private UserDTO seller;

    public ProductDTO(Product product){

        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.seller = new UserDTO(product.getSeller());
    }
}
