package com.example.web_shopapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WebShopItemsDTO {
    List<WebShopItemDTO> products;
    List<CourseDTO> courses;
    
	public WebShopItemsDTO() {
		super();
	}
}
