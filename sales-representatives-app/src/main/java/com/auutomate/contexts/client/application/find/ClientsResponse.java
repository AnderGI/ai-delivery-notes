package com.auutomate.contexts.client.application.find;

import java.util.List;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.Clients;
import com.auutomate.contexts.shared.domain.Response;

public final class ClientsResponse implements Response {
	private final List<Client> clients;
	
	private ClientsResponse(List<Client> clients) {
		this.clients = clients;
	}
	
	public static ClientsResponse fromAggregate(Clients clients) {
		return new ClientsResponse(clients.getAll());
	}
	
	public List<Client> clients(){
		return clients;
	}
}
