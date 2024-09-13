package com.auutomate.contexts.client.domain;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
	Optional<List<Client>> searchAll();
}
