package com.example.web_shopapi.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.web_shopapi.dto.CourseDTO;
import com.example.web_shopapi.dto.InvoiceDTO;
import com.example.web_shopapi.dto.InvoiceItemDTO;
import com.example.web_shopapi.dto.WebShopItemDTO;
import com.example.web_shopapi.dto.WebShopItemsDTO;

@Component
public class InvoiceMapper {
	
	public InvoiceDTO getInvoiceDto(WebShopItemsDTO dto){
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		// ovaj web shop id se dobija prilikom registracije webshopa na psp treba ga negde sacuvati
	    invoiceDTO.setWebShopId("4028808c7dac61d3017dac9517690001");
	    invoiceDTO.setCurrency("RSD");
	    BigDecimal totalAmount = new BigDecimal("0.00");
	    for(WebShopItemDTO wi: dto.getProducts()) {
	    	// unutar ovoga su i kursevi malo je konfuzno
	    	totalAmount = totalAmount.add(BigDecimal.valueOf(wi.getPrice()));
	    }
	    List<InvoiceItemDTO> products = dto.getProducts().stream().map(p -> getInvoiceItemDTO(p)).collect(Collectors.toList());
	    
	    invoiceDTO.setAmount(totalAmount);
	    invoiceDTO.setInvoiceItems(products);
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
