package com.auutomate.contexts.client.application.update;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public final class ClientMailUpdater {
	private final ClientRepository repo;
	
	public ClientMailUpdater(ClientRepository repo) {
		this.repo = repo;
	}
	
	public void update(String id, String mail) throws ClientNotFoundException {
		Client.updateMail(repo, id, mail);
	}
}
