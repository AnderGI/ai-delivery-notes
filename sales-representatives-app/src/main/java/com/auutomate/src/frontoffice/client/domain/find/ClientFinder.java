package com.auutomate.src.frontoffice.client.domain.find;

import com.auutomate.src.backoffice.client_details.domain.ClientId;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.frontoffice.client.domain.Clients;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public final class ClientFinder {
	public static Clients findAll(ClientRepository repo){
		return repo.searchAll().orElse(null);
	}
	
	public static Client find(ClientRepository repo,String id) throws ClientNotFoundException {
		ClientId clientId = new ClientId(id);
		Client c = repo.search(clientId).orElse(null);
		if(c == null) throw new ClientNotFoundException(clientId);
		return c;
	}
}
