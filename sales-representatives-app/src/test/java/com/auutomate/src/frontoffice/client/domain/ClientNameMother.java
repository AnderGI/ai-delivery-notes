package com.auutomate.src.frontoffice.client.domain;

import com.auutomate.src.backoffice.client_details.domain.ClientName;
import com.github.javafaker.Faker;

public final class ClientNameMother {
	public static ClientName random() {
		Faker faker = new Faker();
		return new ClientName(faker.name().name());
	}
	public static ClientName create(String name) {
		return new ClientName(name);
	}
}
