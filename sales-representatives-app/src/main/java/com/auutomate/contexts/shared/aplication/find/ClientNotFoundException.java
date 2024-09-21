package com.auutomate.contexts.shared.aplication.find;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client_details.domain.ClientId;

public final class ClientNotFoundException extends Exception{
	public ClientNotFoundException(ClientId id) {
		super("Client with id <" + id.id() + "> does not exist");
	}
}
