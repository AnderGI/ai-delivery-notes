package com.auutomate.src.frontoffice.client.application.update;

import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public final class ClientNameUpdater {
	private final ClientRepository repo;
	
	public ClientNameUpdater(ClientRepository repo) {
		this.repo = repo;
	}
	
	public void update(String id, String name) throws ClientNotFoundException {
		Client.updateName(repo, id, name);
	}
}
