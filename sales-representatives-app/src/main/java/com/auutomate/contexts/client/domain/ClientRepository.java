package com.auutomate.contexts.client.domain;

import java.util.Optional;

import com.auutomate.contexts.client_details.domain.ClientId;

public interface ClientRepository {
	Optional<Clients> searchAll();
	void save(Client c);
	Optional<Client> search(ClientId id);
}
