package com.auutomate.contexts.client.domain.registar;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client.domain.find.ClientFinder;

public final class ClientRegistar {
	public static void registar(ClientRepository repo, String id, String name, String mail) {
		Client client = Client.fromPrimitives(id, name, mail);
		repo.save(client);
	}
}
