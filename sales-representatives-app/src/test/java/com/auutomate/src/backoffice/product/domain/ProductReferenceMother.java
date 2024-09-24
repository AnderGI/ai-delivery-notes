package com.auutomate.src.backoffice.product.domain;

import com.github.javafaker.Faker;

public final class ProductReferenceMother {
	public static ProductReference random() {
		Faker faker = new Faker();
		String reference = faker.numerify("REF-#########").toString();
		return new ProductReference(reference);
	}
	public static ProductReference create(String reference) {
		return new ProductReference(reference);
	}
}
