package com.auutomate.src.backoffice.product.domain;

import java.text.NumberFormat;

import com.github.javafaker.Faker;
import com.github.javafaker.Number;

public final class ProductPriceMother {
	public static ProductPrice random() {
		Faker faker = new Faker();
		Double priceD = faker.number().randomDouble(3, 0, 10000000);
		String price = NumberFormat.getCurrencyInstance().format(priceD);
		return new ProductPrice(price);
	}
	public static ProductPrice create(String price) {
		return new ProductPrice(price);
	}
}
