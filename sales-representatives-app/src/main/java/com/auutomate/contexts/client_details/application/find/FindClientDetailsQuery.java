package com.auutomate.contexts.client_details.application.find;

import com.auutomate.contexts.shared.domain.Query;

public class FindClientDetailsQuery implements Query{
	private final String id;
	
	public FindClientDetailsQuery(String id) {
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
}
