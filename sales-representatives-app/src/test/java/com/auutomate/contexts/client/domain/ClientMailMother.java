package com.auutomate.contexts.client.domain;

import com.github.javafaker.Faker;

public final class ClientMailMother {
	public static ClientMail random() {
		Faker faker = new Faker();
		return new ClientMail(faker.internet().emailAddress());
	}
	public static ClientMail create(String value) {
		return new ClientMail(value);
	}
}
