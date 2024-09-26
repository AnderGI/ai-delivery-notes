package com.auutomate.src.backoffice.client_orders.application.search;

import com.auutomate.src.backoffice.client_orders.domain.ClientOrders;
import com.auutomate.src.backoffice.client_orders.domain.ClientOrdersRepository;

public final class ClientOrdersSearcher {
	private final ClientOrdersRepository repo;
	
	public ClientOrdersSearcher(ClientOrdersRepository repo) {
		this.repo = repo;
	}
	
	public ClientOrders search(String clientId) {
		return repo.search(clientId);
		
	}
}
