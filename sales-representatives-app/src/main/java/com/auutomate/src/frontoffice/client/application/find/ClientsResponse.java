package com.auutomate.src.frontoffice.client.application.find;

import java.util.List;

import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.Clients;
import com.auutomate.src.shared.domain.Response;

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
