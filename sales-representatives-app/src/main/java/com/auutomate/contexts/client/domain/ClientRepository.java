package com.auutomate.contexts.client.domain;

import java.util.Optional;

public interface ClientRepository {
	Optional<Client> searchAll();
}
