package com.auutomate.src.backoffice.client_orders.application.search;

import com.auutomate.src.shared.domain.Query;

public final class FindClientOrdersQuery implements Query {
	private final String id;
	
	public FindClientOrdersQuery(String id) {
		this.id = id;
	}
	
	public String id() {
		return id;
	}
	
}
