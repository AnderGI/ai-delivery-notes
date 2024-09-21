package com.auutomate.contexts.client.infrastructure.persistence.in_memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client.domain.Clients;
import com.auutomate.contexts.client_details.domain.ClientId;

public class InMemoryClientRepository implements ClientRepository {

	private final Clients database = Clients.empty();
	
	@Override
	public Optional<Clients> searchAll() {
		return Optional.of(database);
	}

	@Override
	public void save(Client c) {
		database.add(c);
	}

	@Override
	public Optional<Client> search(ClientId id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
