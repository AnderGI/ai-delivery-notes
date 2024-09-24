package com.auutomate.src.backoffice.client_details.application.update;

import com.auutomate.src.backoffice.client_details.domain.ClientDetails;
import com.auutomate.src.backoffice.client_details.domain.ClientDetailsRepository;
import com.auutomate.src.shared.domain.EventBus;

public final class ClientDetailsNameUpdater {
	private final ClientDetailsRepository repo;
	private final EventBus bus;
	
	public ClientDetailsNameUpdater(ClientDetailsRepository repo, EventBus bus) {
		this.repo = repo;
		this.bus = bus;
	}
	
	public void update(String id, String name) throws Exception {
		ClientDetails.updateName(repo, bus, id, name);
	}
	
}
