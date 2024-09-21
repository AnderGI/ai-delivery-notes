package com.auutomate.contexts.client_details.domain;

import java.util.Optional;

public interface ClientDetailsRepository {
	Optional<ClientDetails> search(ClientId id);
	void save(ClientDetails details);
}
