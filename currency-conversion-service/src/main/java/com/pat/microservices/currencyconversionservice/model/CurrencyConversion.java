package com.pat.microservices.currencyconversionservice.model;

import java.math.BigDecimal;

public class CurrencyConversion {

	private Long id;
	private String from;
	private String to;
	private BigDecimal quantity;
	private BigDecimal conversion;
	private BigDecimal conversionResult;
	private String environment;

	public CurrencyConversion() {}

	public CurrencyConversion(Long id, String from, String to,
			BigDecimal quantity,
			BigDecimal conversion,
			BigDecimal conversionResult,
			String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversion = conversion;
		this.quantity = quantity;
		this.conversionResult = conversionResult;
		this.environment = environment;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getConversion() {
		return conversion;
	}
	public void setConversion(BigDecimal conversion) {
		this.conversion = conversion;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getConversionResult() {
		return conversionResult;
	}
	public void setConversionResult(BigDecimal conversionResult) {
		this.conversionResult = conversionResult;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "CurrencyConversion [id=" + id + ", from=" + from + ", to=" + to + ", quantity=" + quantity
				+ ", conversion=" + conversion + ", conversionResult=" + conversionResult + ", environment="
				+ environment + "]";
	}

	
}
