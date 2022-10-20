package com.pat.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pat.microservices.currencyconversionservice.model.CurrencyConversion;
import com.pat.microservices.currencyconversionservice.util.currencyExchangeProxy;

@RestController
public class currencyConversionController {

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion currencyConvertedValue(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {

		// RestTemplate used to make API Calls
		// Step 3.1 : map data for RestTemplate().getEntity(3rd param)
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		//step 3 : URL from which it talks to for data
		// for RestTemplate().getEntity(1st param)
		String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

		// step 2.1 extract to local variable
		ResponseEntity<CurrencyConversion> responseEntity =
				// step 2
				new RestTemplate().getForEntity(url, CurrencyConversion.class,uriVariables);

		CurrencyConversion body = responseEntity.getBody();

		// step 1
//		CurrencyConversion currencyConversion =	new CurrencyConversion(1000L,from,to,quantity,BigDecimal.ONE,BigDecimal.ONE,"8100");
		CurrencyConversion currencyConversion =
				new CurrencyConversion(body.getId(),
						from,to,quantity,
						body.getConversion(),
						quantity.multiply(body.getConversion()),
						body.getEnvironment());
		return currencyConversion;
	}

	// using feign
	@Autowired
	private currencyExchangeProxy proxy;

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion FeigncurrencyConvertedValue(
			@PathVariable("from") String from,
			@PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {

		CurrencyConversion response =
				proxy.retrieveExchangeValue(from,to);

//		System.out.println(response);

		return new CurrencyConversion(response.getId(),
						from,to,quantity,
						response.getConversion(),
						quantity.multiply(response.getConversion()),
						response.getEnvironment());
	}

}
