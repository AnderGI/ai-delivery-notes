package com.auutomate.src.frontoffice.client.application.find;

import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.shared.domain.Response;

public final class ClientResponse implements Response {
	private final String id;
	private final String name;
	private final String mail; 
	
	private ClientResponse(String id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}
	
	public static ClientResponse fromAggregate(Client client) {
		return new ClientResponse(client.getId(), client.getName(), client.getMail());
	}
	
	public String id() {
		return this.id;
	}
	
	public String name() {
		return this.name;
	}
	
	public String mail() {
		return this.mail;
	}
	
}
