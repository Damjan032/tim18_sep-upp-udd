package com.example.web_shopapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;


import com.example.web_shopapi.dto.InvoiceDTO;
import com.example.web_shopapi.dto.WebShopItemsDTO;
import com.example.web_shopapi.mapper.InvoiceMapper;
import com.example.web_shopapi.utils.Constants;


@RestController
@RequestMapping(path = "api/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
	
	@Autowired
	private InvoiceMapper invoiceMapper;
	
	
	@PostMapping
    public ResponseEntity<String> createInvoice(@Valid @RequestBody WebShopItemsDTO dto) {
        InvoiceDTO invoice = invoiceMapper.getInvoiceDto(dto);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                String.format(Constants.GATEWAY + Constants.API_INVOICE),
                HttpMethod.POST, new HttpEntity<>(invoice), String.class);
        return response;
    }

}
