package com.auutomate.src.backoffice.client_details.application.find;

import com.auutomate.src.shared.domain.Query;

public class FindClientDetailsQuery implements Query{
	private final String id;
	
	public FindClientDetailsQuery(String id) {
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
}
