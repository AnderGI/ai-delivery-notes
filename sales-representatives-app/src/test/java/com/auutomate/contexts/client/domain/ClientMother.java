package com.auutomate.contexts.client.domain;

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
