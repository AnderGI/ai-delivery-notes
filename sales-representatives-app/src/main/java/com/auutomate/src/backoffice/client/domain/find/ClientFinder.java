package com.auutomate.src.backoffice.client.domain.find;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.backoffice.client.domain.ClientId;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public final class ClientFinder {
	public static Client find(ClientRepository repo, String id) throws ClientNotFoundException {
		Client details = repo.search(new ClientId(id)).orElse(null);
		if(details == null) throw new ClientNotFoundException(new ClientId(id));
		return details;
	}
}
