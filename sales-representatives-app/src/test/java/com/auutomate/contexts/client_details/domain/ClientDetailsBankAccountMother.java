package com.auutomate.contexts.client_details.domain;

import com.github.javafaker.Faker;

public final class ClientDetailsBankAccountMother {
	public static ClientDetailsBankAccount random() {
		Faker faker = new Faker();
		// should be changed to bank account number
		return new ClientDetailsBankAccount(faker.business().creditCardNumber()); 
	}
	public static ClientDetailsBankAccount create(String number) {
		return new ClientDetailsBankAccount(number); 
	}
}
