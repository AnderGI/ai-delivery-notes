package com.auutomate.src.backoffice.product.application.update;

import com.auutomate.src.shared.domain.Command;

public final class UpdateProductPriceCommand implements Command {
	private final String reference;
	private final String price;
	
	public UpdateProductPriceCommand(String reference, String price) {
		this.reference = reference;
		this.price = price;
	}
	
	public String reference() {
		return reference;
	}
	
	public String price() {
		return price;
	}
	
}
