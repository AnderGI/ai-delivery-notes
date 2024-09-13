package com.auutomate.contexts.client.application.find;

import java.util.List;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;

public final class ClientFinder {
	private ClientRepository repo;
	
	public ClientFinder(ClientRepository repo) {
		this.repo = repo;
	}

	public List<Client> findAll(){
		return this.repo.searchAll().orElse(null);
	}
}
