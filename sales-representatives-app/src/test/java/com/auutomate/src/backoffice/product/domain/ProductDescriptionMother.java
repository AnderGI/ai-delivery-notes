package com.auutomate.src.backoffice.product.domain;

import com.github.javafaker.Faker;

public final class ProductDescriptionMother {
	public static ProductDescription random() {
		Faker faker = new Faker();
		String description = faker.lorem().sentence();
		return new ProductDescription(description);
	}
	public static ProductDescription create(String description) {
		return new ProductDescription(description);
	}
}
