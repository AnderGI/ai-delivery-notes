package com.auutomate.src.backoffice.client_details.application.update;

import com.auutomate.src.backoffice.client_details.domain.ClientDetails;
import com.auutomate.src.backoffice.client_details.domain.ClientDetailsRepository;
import com.auutomate.src.shared.domain.EventBus;

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
