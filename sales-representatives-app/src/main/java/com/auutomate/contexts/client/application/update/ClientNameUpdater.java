package com.auutomate.contexts.client.application.update;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public final class ClientNameUpdater {
	private final ClientRepository repo;
	
	public ClientNameUpdater(ClientRepository repo) {
		this.repo = repo;
	}
	
	public void update(String id, String name) throws ClientNotFoundException {
		Client.updateName(repo, id, name);
	}
}
