package com.auutomate.contexts.client_details.domain.find;

import com.auutomate.contexts.client.application.find.ClientResponse;
import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.ClientMail;
import com.auutomate.contexts.client_details.domain.ClientName;
import com.auutomate.contexts.shared.domain.Response;

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
