package com.auutomate.src.backoffice.client.domain.update;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.backoffice.client.domain.ClientId;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class ClientNameUpdater {
	public static void update(ClientRepository repo, EventBus bus, String id, String name) throws Exception {
		ClientId clientId = new ClientId(id);
		Client client = repo.search(clientId).orElse(null);
		if(client == null) throw new ClientNotFoundException(clientId);
		
		if(!client.nameValue().equals(name)) {
			client.updateName(name);
			repo.save(client);
			bus.publish(new ClientNameUpdatedDomainEvent(id, name));
		}
	}
}
