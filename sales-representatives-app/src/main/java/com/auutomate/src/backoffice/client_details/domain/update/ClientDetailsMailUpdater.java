package com.auutomate.src.backoffice.client_details.domain.update;

import com.auutomate.src.backoffice.client_details.domain.ClientDetails;
import com.auutomate.src.backoffice.client_details.domain.ClientDetailsRepository;
import com.auutomate.src.backoffice.client_details.domain.ClientId;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class ClientDetailsMailUpdater {
	public static void update(ClientDetailsRepository repo, EventBus bus, String id, String mail) throws Exception {
		ClientId clientId = new ClientId(id);
		ClientDetails client = repo.search(clientId).orElse(null);
		if(client == null) throw new ClientNotFoundException(clientId);
		
		if(!client.mailValue().equals(mail)) {
			client.updateMail(mail);
			repo.save(client);
			bus.publish(new ClientMailUpdatedDomainEvent(id, mail));
		}
	}
}
