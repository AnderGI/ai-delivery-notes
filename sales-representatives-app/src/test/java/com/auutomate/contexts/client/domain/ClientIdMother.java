package com.auutomate.contexts.client.domain;

import java.util.UUID;

import com.auutomate.contexts.client_details.domain.ClientId;


public final class ClientIdMother {
	public static ClientId random() {
		String uuid = UUID.randomUUID().toString();
		return new ClientId(uuid);
	}

	public static ClientId create(String uuid) {
		return new ClientId(uuid);
	}
}
