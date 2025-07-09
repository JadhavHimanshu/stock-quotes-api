package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class StockResponse {
	private String message;
	private String Code;
	private String symbol;
	private Double price;
	private Double change;
	private Double percentChange;
	private Long timestamp;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public Double getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public StockResponse(String message, Double price, Double change, Double percentChange, Long localDateTime) {
		super();
//		this.message = message;
//		Code = code;
		this.symbol = symbol;
		this.price = price;
		this.change = change;
		this.percentChange = percentChange;
		this.timestamp = localDateTime;
	}

	public StockResponse() {
	}

}
