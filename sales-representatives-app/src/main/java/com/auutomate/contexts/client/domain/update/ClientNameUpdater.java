package com.auutomate.contexts.client.domain.update;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public final class ClientNameUpdater {

	public static void update(ClientRepository repo, String id, String name) throws ClientNotFoundException {
		ClientId clientId = new ClientId(id);
		Client client = repo.search(clientId).orElse(null);
		if(client == null) throw new ClientNotFoundException(clientId);
		if(!client.getName().equals(name)) {
			client.updateMail(name);
			repo.save(client);
		}

	}

}
