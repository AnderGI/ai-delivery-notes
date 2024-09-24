package com.auutomate.src.backoffice.client.application.find;

import com.auutomate.src.shared.domain.Query;

public class FindClientQuery implements Query{
	private final String id;
	
	public FindClientQuery(String id) {
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
}
