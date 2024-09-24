package com.auutomate.src.backoffice.client.application.find;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public final class ClientFinder {
	private ClientRepository repo;
	
	public ClientFinder(ClientRepository repo) {
		this.repo = repo;
	}
	
	public Client find(String id) throws ClientNotFoundException {
		return Client.find(repo, id);
	}
}
