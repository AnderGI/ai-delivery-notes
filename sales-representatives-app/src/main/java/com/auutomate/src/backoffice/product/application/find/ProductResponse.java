package com.auutomate.src.backoffice.product.application.find;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.shared.domain.Response;

public final class ProductResponse implements Response{
	private final String reference;
	private final String price;
	private final String description;
	
	private ProductResponse(String reference, String price, String description) {
		this.reference = reference;
		this.price = price;
		this.description = description;
	}
	
	public static ProductResponse fromProduct(Product product) {
		return new ProductResponse(product.reference(), product.price(), product.description());
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
