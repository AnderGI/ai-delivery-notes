package com.auutomate.contexts.client.domain;

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
