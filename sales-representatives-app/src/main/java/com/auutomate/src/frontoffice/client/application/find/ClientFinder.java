package com.auutomate.src.frontoffice.client.application.find;

import com.auutomate.src.backoffice.client.domain.ClientId;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.frontoffice.client.domain.Clients;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public final class ClientFinder {
	private ClientRepository repo;
	
	public ClientFinder(ClientRepository repo) {
		this.repo = repo;
	}

	public Clients findAll(){
		return this.repo.searchAll().orElse(null);
	}
	
	public Client find(String id) throws ClientNotFoundException {
		ClientId clientId = new ClientId(id);
		Client c = this.repo.search(clientId).orElse(null);
		if(c == null) throw new ClientNotFoundException(clientId);
		return c;
	}
}
