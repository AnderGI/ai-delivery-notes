package com.auutomate.src.backoffice.product.application.find;

import com.auutomate.src.backoffice.product.domain.Products;
import com.auutomate.src.shared.domain.Response;

public final class ProductsResponse implements Response{
	
	private final Products products;
	
	private ProductsResponse(Products products) {
		this.products = products;
	}
	
	public static ProductsResponse fromProducts(Products products) {
		return new ProductsResponse(products);
	}
	
	public Products products() {
		return products;
	}
}
