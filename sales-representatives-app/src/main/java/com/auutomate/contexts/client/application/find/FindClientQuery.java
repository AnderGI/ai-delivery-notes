package com.auutomate.contexts.client.application.find;

import com.auutomate.contexts.shared.domain.Query;

public final class FindClientQuery implements Query {
	private final String clientId;
	
	public FindClientQuery(String id) {
		this.clientId = id;
	}
	
	public String clientId() {
		return this.clientId;
	}
}
