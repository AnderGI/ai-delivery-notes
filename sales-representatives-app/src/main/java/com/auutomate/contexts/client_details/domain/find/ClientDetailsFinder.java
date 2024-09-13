package com.auutomate.contexts.client_details.domain.find;

import com.auutomate.contexts.client.domain.ClientId;
import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;

public final class ClientDetailsFinder {
	public static ClientDetails find(ClientDetailsRepository repo, String id) throws ClientNotFoundException {
		ClientDetails details = repo.search(new ClientId(id)).orElse(null);
		if(details == null) throw new ClientNotFoundException("Client with id <" + id + "> was not found.");
		return details;
	}
}
