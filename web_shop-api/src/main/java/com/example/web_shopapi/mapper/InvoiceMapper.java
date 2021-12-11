package com.example.web_shopapi.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.web_shopapi.dto.CourseDTO;
import com.example.web_shopapi.dto.InvoiceDTO;
import com.example.web_shopapi.dto.InvoiceItemDTO;
import com.example.web_shopapi.dto.WebShopItemDTO;
import com.example.web_shopapi.dto.WebShopItemsDTO;

public class InvoiceMapper {
	
	public InvoiceDTO getInvoiceDto(WebShopItemsDTO dto){
		InvoiceDTO invoiceDTO = new InvoiceDTO();
	    invoiceDTO.setWebShopId(null);
	    invoiceDTO.setCurrency(null);
	    BigDecimal totalAmount = new BigDecimal("0.00");
	    for(WebShopItemDTO wi: dto.getProducts()) {
	    	totalAmount = totalAmount.add(BigDecimal.valueOf(wi.getPrice()));
	    }
	    for(CourseDTO wi: dto.getCourses()) {
	    	totalAmount = totalAmount.add(BigDecimal.valueOf(wi.getPrice()));
	    }
	    List<InvoiceItemDTO> products = dto.getProducts().stream().map(p -> getInvoiceItemDTO(p)).collect(Collectors.toList());
	    List<InvoiceItemDTO> courses = dto.getCourses().stream().map(c -> getInvoiceItemDTO(c)).collect(Collectors.toList());
	    List<InvoiceItemDTO> allItems = Stream.concat(products.stream(), courses.stream())
                .collect(Collectors.toList());
	    invoiceDTO.setAmount(totalAmount);
	    invoiceDTO.setInvoiceItems(allItems);
	    return invoiceDTO;
    }
	
	public InvoiceItemDTO getInvoiceItemDTO(WebShopItemDTO dto) {
		InvoiceItemDTO i = new InvoiceItemDTO();
		i.setAmount(BigDecimal.valueOf(dto.getPrice()));
		i.setCurrency("");
		i.setDescription(dto.getDescription());
		i.setName(dto.getName());
		return i;
	}
	
	public InvoiceItemDTO getInvoiceItemDTO(CourseDTO dto) {
		InvoiceItemDTO i = new InvoiceItemDTO();
		i.setAmount(BigDecimal.valueOf(dto.getPrice()));
		i.setCurrency("");
		i.setDescription(dto.getDescription());
		i.setName(dto.getName());
		return i;
	}
   
}
