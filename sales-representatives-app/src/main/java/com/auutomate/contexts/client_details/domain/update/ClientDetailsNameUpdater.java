package com.auutomate.contexts.client_details.domain.update;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetailsNameUpdater {
	public static void update(ClientDetailsRepository repo, EventBus bus, String id, String name) throws Exception {
		ClientId clientId = new ClientId(id);
		ClientDetails client = repo.search(clientId).orElse(null);
		if(client == null) throw new ClientNotFoundException(clientId);
		
		if(!client.nameValue().equals(name)) {
			client.updateName(name);
			repo.save(client);
			bus.publish(new ClientNameUpdatedDomainEvent(id, name));
		}
	}
}
