package com.auutomate.contexts.client_details.domain;

import java.util.Optional;

import com.auutomate.contexts.client.domain.ClientId;

public interface ClientDetailsRepository {
	Optional<ClientDetails> search(ClientId id);
	void save(ClientDetails details);
}
