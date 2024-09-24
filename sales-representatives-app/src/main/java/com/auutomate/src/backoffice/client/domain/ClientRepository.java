package com.auutomate.src.backoffice.client.domain;

import java.util.Optional;

public interface ClientRepository {
	Optional<Client> search(ClientId id);
	void save(Client details);
}
