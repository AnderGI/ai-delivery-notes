package com.auutomate.contexts.client.domain;

public final class Client {
	private final ClientId id;
	private final ClientName name;
	private final ClientNID nid; // dni

	private Client(ClientId id, ClientName name, ClientNID nid) {
		this.id = id;
		this.name = name;
		this.nid = nid;
	}

	public static Client fromPrimitives(String id, String name, String nid) {
		return new Client(new ClientId(id), new ClientName(name), new ClientNID(nid));
	}

}
