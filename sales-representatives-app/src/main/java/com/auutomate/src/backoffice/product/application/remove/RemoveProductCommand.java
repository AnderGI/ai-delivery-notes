package com.auutomate.src.backoffice.product.application.remove;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.shared.domain.Command;

public final class RemoveProductCommand implements Command {
	private final String reference;
	
	private RemoveProductCommand(String reference) {
		this.reference = reference;
	}
	
	public static RemoveProductCommand fromProduct(Product product) {
		return new RemoveProductCommand(product.reference());
	}
	
	public String reference() {
		return reference;
	}
	
	
	
}
