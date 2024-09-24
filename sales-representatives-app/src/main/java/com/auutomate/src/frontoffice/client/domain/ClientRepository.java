package com.auutomate.src.frontoffice.client.domain;

import java.util.Optional;

import com.auutomate.src.backoffice.client_details.domain.ClientId;

public interface ClientRepository {
	Optional<Clients> searchAll();
	void save(Client c);
	Optional<Client> search(ClientId id);
}
