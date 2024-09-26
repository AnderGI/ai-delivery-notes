package com.auutomate.src.backoffice.client_orders.domain;

public final class Product {
	private String productReference;
	private String price;
	private String description;
	
	public Product(String reference, String price, String description) {
		this.productReference = reference;
		this.price = price;
		this.description = description;
	}
	
	public String reference() {
		return productReference;
	}
	
	public String price() {
		return price;
	}
	
	public String description() {
		return description;
	}
	
}
