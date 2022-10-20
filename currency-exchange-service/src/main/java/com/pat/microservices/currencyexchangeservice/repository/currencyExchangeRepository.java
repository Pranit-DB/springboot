package com.pat.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pat.microservices.currencyexchangeservice.model.CurrencyExchange;

public interface currencyExchangeRepository
extends JpaRepository<CurrencyExchange,Long>{

	// Implementation is provided by JPA
	// JPA converts this into SQL query to query the db table
	CurrencyExchange findByFromAndTo(String from,String to);
}
