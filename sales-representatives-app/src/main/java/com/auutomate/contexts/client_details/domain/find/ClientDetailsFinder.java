package com.auutomate.contexts.client_details.domain.find;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public final class ClientDetailsFinder {
	public static ClientDetails find(ClientDetailsRepository repo, String id) throws ClientNotFoundException {
		ClientDetails details = repo.search(new ClientId(id)).orElse(null);
		if(details == null) throw new ClientNotFoundException(new ClientId(id));
		return details;
	}
}
