package com.auutomate.src.backoffice.client.application.update;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.shared.domain.EventBus;

public final class ClientNameUpdater {
	private final ClientRepository repo;
	private final EventBus bus;
	
	public ClientNameUpdater(ClientRepository repo, EventBus bus) {
		this.repo = repo;
		this.bus = bus;
	}
	
	public void update(String id, String name) throws Exception {
		Client.updateName(repo, bus, id, name);
	}
	
}
