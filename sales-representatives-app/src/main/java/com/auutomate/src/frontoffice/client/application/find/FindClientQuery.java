package com.auutomate.src.frontoffice.client.application.find;

import com.auutomate.src.shared.domain.Query;

public final class FindClientQuery implements Query {
	private final String clientId;
	
	public FindClientQuery(String id) {
		this.clientId = id;
	}
	
	public String clientId() {
		return this.clientId;
	}
}
