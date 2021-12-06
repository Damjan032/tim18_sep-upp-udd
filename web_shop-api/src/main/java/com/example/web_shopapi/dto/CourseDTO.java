package com.example.web_shopapi.dto;

import com.example.web_shopapi.model.Course;
import com.example.web_shopapi.model.Product;
import com.example.web_shopapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private UserDTO seller;

    public CourseDTO(Course course){
        this.id = course.getId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.price = course.getPrice();
        this.seller = new UserDTO(course.getSeller());
    }
}
