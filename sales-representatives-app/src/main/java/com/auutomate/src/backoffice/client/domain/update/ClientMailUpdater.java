package com.auutomate.src.backoffice.client.domain.update;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.backoffice.client.domain.ClientId;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class ClientMailUpdater {
	public static void update(ClientRepository repo, EventBus bus, String id, String mail) throws Exception {
		ClientId clientId = new ClientId(id);
		Client client = repo.search(clientId).orElse(null);
		if(client == null) throw new ClientNotFoundException(clientId);
		
		if(!client.mailValue().equals(mail)) {
			client.updateMail(mail);
			repo.save(client);
			bus.publish(new ClientMailUpdatedDomainEvent(id, mail));
		}
	}
}
