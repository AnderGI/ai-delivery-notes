package com.auutomate.src.backoffice.client_orders.domain;

import java.util.List;

import com.auutomate.src.shared.domain.Response;

public final class ClientOrders implements Response{
	private final String clientId;
	private List<Order> orders; 
	
	public ClientOrders(String clientId, List<Order> orders) {
		this.clientId = clientId;
		this.orders = orders;
	}
	
	public String id() {
		return clientId;
	}
	
	public List<Order> orders(){
		return orders;
	}
	
}
