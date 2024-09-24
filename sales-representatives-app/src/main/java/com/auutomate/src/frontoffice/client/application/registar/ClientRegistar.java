package com.auutomate.src.frontoffice.client.application.registar;

import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;

public final class ClientRegistar {
	private final ClientRepository repo;
	
	public ClientRegistar(ClientRepository repo) {
		this.repo = repo;
	}
	
	public void registar(String id, String name, String mail) {
		Client.registar(repo, id, name, mail);
	}
}
