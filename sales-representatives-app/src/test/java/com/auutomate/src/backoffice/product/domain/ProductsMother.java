package com.auutomate.src.backoffice.product.domain;

public final class ProductsMother {
	public static Products createFromValues(Product...products) {
		return Products.fromValues(products);
	}
	
	public static Products createEmpty() {
		return Products.empty();
	}

}
