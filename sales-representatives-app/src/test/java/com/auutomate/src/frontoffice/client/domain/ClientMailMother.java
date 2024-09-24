package com.auutomate.src.frontoffice.client.domain;

import com.auutomate.src.backoffice.client.domain.ClientMail;
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
