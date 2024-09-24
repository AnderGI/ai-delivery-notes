package com.auutomate.src.backoffice.product.application.save;

import com.auutomate.src.shared.domain.Command;

public final class SaveProductCommand implements Command {
	private final String reference;
	private final String price;
	private final String description;
	
	public SaveProductCommand(String reference, String price, String description) {
		this.reference = reference;
		this.price = price;
		this.description = description;
	}
	
	public String reference() {
		return reference;
	}
	
	public String price() {
		return price;
	}
	
	public String description() {
		return description;
	}
	
}
