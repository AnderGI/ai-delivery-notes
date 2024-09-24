package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientIdMother;
import com.auutomate.src.frontoffice.client.domain.ClientMailMother;
import com.auutomate.src.frontoffice.client.domain.ClientNameMother;

public final class ClientDetailsObjectMother {
	public static Client random() {
		return new Client(
				ClientIdMother.random().id(),
				ClientNameMother.random().name(),
				ClientMailMother.random().mail()
		);
	}
	public static Client create(String id, String name, String mail) {
		return new Client(id, name, mail);
	}
}
