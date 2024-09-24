package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client.domain.ClientBankAccount;
import com.github.javafaker.Faker;

public final class ClientDetailsBankAccountMother {
	public static ClientBankAccount random() {
		Faker faker = new Faker();
		// should be changed to bank account number
		return new ClientBankAccount(faker.business().creditCardNumber()); 
	}
	public static ClientBankAccount create(String number) {
		return new ClientBankAccount(number); 
	}
}
