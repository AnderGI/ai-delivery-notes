package com.auutomate.src.backoffice.product.application.find;

import com.auutomate.src.shared.domain.Query;

public final class FindProductQuery implements Query {
	private final String reference;
	
	public FindProductQuery(String reference) {
		this.reference = reference;
	}
	
	public String reference() {
		return reference;
	}
	
}
