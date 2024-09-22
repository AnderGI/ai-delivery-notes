package com.auutomate.contexts.client_details.application.update;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetailsMailUpdater {
	private final ClientDetailsRepository repo;
	private final EventBus bus;
	
	public ClientDetailsMailUpdater(ClientDetailsRepository repo, EventBus bus) {
		this.repo = repo;
		this.bus = bus;
	}
	
	public void update(String id, String mail) throws Exception {
		ClientDetails.updateMail(repo, bus, id, mail);
	}
}
