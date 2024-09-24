package com.auutomate.src.backoffice.client_details.domain;

import java.util.Optional;

public interface ClientDetailsRepository {
	Optional<ClientDetails> search(ClientId id);
	void save(ClientDetails details);
}
