package com.auutomate.src.backoffice.client_details.domain.find;

import com.auutomate.src.backoffice.client_details.domain.ClientDetails;
import com.auutomate.src.shared.domain.Response;

public final class ClientDetailsResponse implements Response{
	private final String id;
	private final String name;
	private final String mail;
	
	public ClientDetailsResponse(String id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}
	
	public static ClientDetailsResponse fromAggregate(ClientDetails client) {
		return new ClientDetailsResponse(client.idValue(), client.nameValue(), client.mailValue());
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
