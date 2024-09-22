package com.auutomate.contexts.client.application.registar;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;

public final class ClientRegistar {
	private final ClientRepository repo;
	
	public ClientRegistar(ClientRepository repo) {
		this.repo = repo;
	}
	
	public void registar(String id, String name, String mail) {
		Client.registar(repo, id, name, mail);
	}
}
