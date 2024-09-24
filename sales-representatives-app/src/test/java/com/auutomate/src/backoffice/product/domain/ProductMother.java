package com.auutomate.src.backoffice.product.domain;

public final class ProductMother {
	public static Product random() {
		return new Product(
				ProductReferenceMother.random().reference(), 
				ProductPriceMother.random().price(), 
				ProductDescriptionMother.random().description()
			);
	}
	public static Product create(String reference, String price, String description) {
		return new Product(reference, price, description);
	}
}
