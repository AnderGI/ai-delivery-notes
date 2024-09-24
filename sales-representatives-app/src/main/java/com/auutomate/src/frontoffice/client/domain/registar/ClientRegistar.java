package com.auutomate.src.frontoffice.client.domain.registar;

import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;

public final class ClientRegistar {
	public static void registar(ClientRepository repo, String id, String name, String mail) {
		Client client = Client.fromPrimitives(id, name, mail);
		repo.save(client);
	}
}
