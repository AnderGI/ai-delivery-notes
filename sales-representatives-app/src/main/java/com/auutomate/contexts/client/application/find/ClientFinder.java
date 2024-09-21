package com.auutomate.contexts.client.application.find;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client.domain.Clients;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public final class ClientFinder {
	private ClientRepository repo;
	
	public ClientFinder(ClientRepository repo) {
		this.repo = repo;
	}

	public Clients findAll(){
		return this.repo.searchAll().orElse(null);
	}
	
	public Client find(ClientId id) throws ClientNotFoundException {
		Client c = this.repo.search(id).orElse(null);
		if(c == null) throw new ClientNotFoundException(id);
		return c;
	}
}
