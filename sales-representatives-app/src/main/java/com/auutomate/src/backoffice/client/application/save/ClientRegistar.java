package com.auutomate.src.backoffice.client.application.save;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.shared.domain.EventBus;

public final class ClientRegistar {
	private final ClientRepository repo;
	private final EventBus eventBus;
	public ClientRegistar(ClientRepository repo, EventBus eventBus) {
		this.repo = repo;
		this.eventBus = eventBus;
	}
	
	public void registar(String id, String name, String mail) throws Exception {
		Client
				.create(eventBus, repo, id, name, mail);
	}
}
