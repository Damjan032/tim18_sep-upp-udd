package com.example.web_shopapi.controller;

import javax.validation.Valid;

import com.example.web_shopapi.dto.PaypalBtcTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


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
        System.out.println(response.getBody());
        return response;
    }

    @PostMapping("/pp-btc-transaction")
    public ResponseEntity<String> saveTransaction(@RequestBody PaypalBtcTransactionDTO paypalBtcTransactionDTO){
        //InvoiceDTO invoice = invoiceMapper.getInvoiceDto(paypalBtcTransactionDTO);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                String.format(Constants.GATEWAY + Constants.API_INVOICE + "/pp-btc-transaction"),
                HttpMethod.POST, new HttpEntity<>(paypalBtcTransactionDTO), String.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

}
