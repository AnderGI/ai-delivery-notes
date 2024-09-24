package com.auutomate.src.frontoffice.client.domain;

import com.auutomate.src.frontoffice.client.domain.Client;

public final class ClientMother {
	public static Client random() {
		return Client.fromPrimitives(
				ClientIdMother.random().id(), 
				ClientNameMother.random().name(), 
				ClientNIDMother.random().nid());
	}
	public static Client create(String id, String name, String NID) {
		return Client.fromPrimitives(id, name, NID);
	}

}
