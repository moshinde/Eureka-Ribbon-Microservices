package com.monica.microservices.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.monica.microservices.model.CurrencyConversionBean;
import com.monica.microservices.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) throws IOException {
		
		ResponseEntity<CurrencyConversionBean> responseEntity= new RestTemplate().getForEntity("http://localhost:8300/currency-exchange/from/"+from+"/to/"+to, CurrencyConversionBean.class);
		CurrencyConversionBean response = responseEntity.getBody();
		return new CurrencyConversionBean(from, to, response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()));
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		CurrencyConversionBean cb=currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
		return new CurrencyConversionBean(from, to, cb.getConversionMultiple(), quantity, quantity.multiply(cb.getConversionMultiple()));
	}

}
