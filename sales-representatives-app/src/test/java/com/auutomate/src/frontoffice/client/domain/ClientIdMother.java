package com.auutomate.src.frontoffice.client.domain;

import java.util.UUID;

import com.auutomate.src.backoffice.client.domain.ClientId;


public final class ClientIdMother {
	public static ClientId random() {
		String uuid = UUID.randomUUID().toString();
		return new ClientId(uuid);
	}

	public static ClientId create(String uuid) {
		return new ClientId(uuid);
	}
}
