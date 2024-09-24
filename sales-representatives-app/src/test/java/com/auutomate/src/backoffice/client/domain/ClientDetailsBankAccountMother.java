package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client_details.domain.ClientDetailsBankAccount;
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
