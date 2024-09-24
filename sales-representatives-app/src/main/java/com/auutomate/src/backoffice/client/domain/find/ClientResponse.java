package com.auutomate.src.backoffice.client.domain.find;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.shared.domain.Response;

public final class ClientResponse implements Response{
	private final String id;
	private final String name;
	private final String mail;
	
	public ClientResponse(String id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}
	
	public static ClientResponse fromAggregate(Client client) {
		return new ClientResponse(client.idValue(), client.nameValue(), client.mailValue());
	}
	
	public String id() {
		return id;
	}

	public String name() {
		return name;
	}
	
	public String mail() {
		return mail;
	}
}
