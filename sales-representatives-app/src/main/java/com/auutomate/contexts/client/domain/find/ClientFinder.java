package com.auutomate.contexts.client.domain.find;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client.domain.Clients;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public final class ClientFinder {
	public static Clients findAll(ClientRepository repo){
		return repo.searchAll().orElse(null);
	}
	
	public static Client find(ClientRepository repo,String id) throws ClientNotFoundException {
		ClientId clientId = new ClientId(id);
		Client c = repo.search(clientId).orElse(null);
		if(c == null) throw new ClientNotFoundException(clientId);
		return c;
	}
}
