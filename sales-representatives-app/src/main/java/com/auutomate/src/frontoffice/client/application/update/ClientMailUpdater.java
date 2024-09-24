package com.auutomate.src.frontoffice.client.application.update;

import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public final class ClientMailUpdater {
	private final ClientRepository repo;
	
	public ClientMailUpdater(ClientRepository repo) {
		this.repo = repo;
	}
	
	public void update(String id, String mail) throws ClientNotFoundException {
		Client.updateMail(repo, id, mail);
	}
}
