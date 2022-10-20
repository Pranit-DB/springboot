package com.pat.microservices.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pat.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.pat.microservices.currencyexchangeservice.repository.currencyExchangeRepository;

@RestController
public class currencyExchangeController {

	@Autowired // step 4
	private currencyExchangeRepository currencyRepo;

	@Autowired // step 3 : from core.env
	private Environment environment;

//	@GetMapping("/currency-exchange/from/USD/to/INR")
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to) {
		// step 1
//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L,from,to,BigDecimal.valueOf(60));

		// step 4.1
		CurrencyExchange currencyExchange =
				currencyRepo.findByFromAndTo(from,to);
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find data for"+from+"to"+to);
		}

		//step 3.1
		String port = environment.getProperty("server.port");
		//step 2
		currencyExchange.setEnvironment(port);

		return currencyExchange;
	}
}
