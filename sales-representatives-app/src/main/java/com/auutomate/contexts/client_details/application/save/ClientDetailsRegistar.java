package com.auutomate.contexts.client_details.application.save;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetailsRegistar {
	private final ClientDetailsRepository repo;
	private final EventBus eventBus;
	public ClientDetailsRegistar(ClientDetailsRepository repo, EventBus eventBus) {
		this.repo = repo;
		this.eventBus = eventBus;
	}
	
	public void registar(String id, String name, String mail) throws Exception {
		ClientDetails
				.create(eventBus, repo, id, name, mail);
	}
}
