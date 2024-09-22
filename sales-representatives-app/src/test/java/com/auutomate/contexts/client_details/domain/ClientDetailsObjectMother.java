package com.auutomate.contexts.client_details.domain;

import com.auutomate.contexts.client.domain.ClientIdMother;
import com.auutomate.contexts.client.domain.ClientMailMother;
import com.auutomate.contexts.client.domain.ClientNIDMother;
import com.auutomate.contexts.client.domain.ClientNameMother;

public final class ClientDetailsObjectMother {
	public static ClientDetails random() {
		return new ClientDetails(
				ClientIdMother.random().id(),
				ClientNameMother.random().name(),
				ClientMailMother.random().mail()
		);
	}
	public static ClientDetails create(String id, String name, String mail) {
		return new ClientDetails(id, name, mail);
	}
}
