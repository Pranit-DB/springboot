package com.pat.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
@SuppressWarnings("unused")
@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
//	@Retry(name = "sample-api" , fallbackMethod = "HardCodedResponse")
	// if microservice fails to respond it'll throw a fallback method
//	@CircuitBreaker(name = "default" , fallbackMethod = "HardCodedResponse")
	// in 10s allow only 10000 calls to perticular service is RateLimiter
//	@RateLimiter(name = "default")
	// how many concurrent calls are allowed
	@Bulkhead(name = "sample-api")
	public String sampleApi() {
		logger.info("sample API call recieved");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-url",
//				String.class);
//		return forEntity.getBody();
			return "sampleApi";
	}

	public String HardCodedResponse(Exception ex) {
		return "FallBack Response";
	}
}
