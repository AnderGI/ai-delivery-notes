package com.auutomate.src.backoffice.client_orders.domain;

import java.util.List;

public final class Order {
	private String orderId;
	private List<Product> products;
	private String createdOn;
	
	public Order(String id, String createdOn, List<Product> products) {
		this.orderId = id;
		this.createdOn = createdOn;
		this.products = products;
	}
	
	public String id() {
		return orderId;
	}
	
	public String createdOn() {
		return createdOn;
	}
	
	public List<Product> products(){
		return products;
	}
	
}
