package com.monica.microservices.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monica.microservices.model.ExchangeValue;



@RestController
public class CurrencyExchangeController {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response
		  = restTemplate.getForEntity("https://api.exchangerate-api.com/v4/latest/" + from, String.class);
		ObjectMapper mapper = new ObjectMapper();
		return  new ExchangeValue(from,to, new BigDecimal(mapper.readTree(response.getBody()).path("rates").path(to).toString()));
	}
}