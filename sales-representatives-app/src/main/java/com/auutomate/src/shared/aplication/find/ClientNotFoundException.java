package com.auutomate.src.shared.aplication.find;

import com.auutomate.src.backoffice.client_details.domain.ClientId;

public final class ClientNotFoundException extends Exception{
	public ClientNotFoundException(ClientId id) {
		super("Client with id <" + id.id() + "> does not exist");
	}
}
