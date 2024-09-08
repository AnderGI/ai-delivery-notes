package com.auutomate.contexts.client.domain;

public final class Client {
	private final ClientId id;
	private final ClientName name;
	private final ClientNID nid; // dni
	//private ClientAddress address; // entity? 
	public Client(String id, String name, String nid) {
		this.id = new ClientId(id);
		this.name = new ClientName(name);
		this.nid = new ClientNID(nid);
	}
	

}
