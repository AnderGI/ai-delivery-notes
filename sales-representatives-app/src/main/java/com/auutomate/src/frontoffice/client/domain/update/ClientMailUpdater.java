package com.auutomate.src.frontoffice.client.domain.update;

import com.auutomate.src.backoffice.client_details.domain.ClientId;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public final class ClientMailUpdater {
	public static void update(ClientRepository repo,String id, String mail) throws ClientNotFoundException {
		ClientId clientId = new ClientId(id);
		Client client = repo.search(clientId).orElse(null);
		if(client == null) throw new ClientNotFoundException(clientId);
		if(!client.getMail().equals(mail)) {
			client.updateMail(mail);
			repo.save(client);
		}
	}
}
