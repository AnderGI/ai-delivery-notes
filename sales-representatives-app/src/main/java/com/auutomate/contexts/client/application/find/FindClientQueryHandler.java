package com.auutomate.contexts.client.application.find;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.QueryHandler;

public final class FindClientQueryHandler implements QueryHandler<FindClientQuery, ClientResponse> {

	private ClientFinder finder;
	
	public FindClientQueryHandler(ClientFinder finder) {
		this.finder = finder;
	}

	@Override
	public ClientResponse handle(FindClientQuery query) throws ClientNotFoundException {
		// TODO Auto-generated method stub
		Client client = finder.find(query.clientId());
		return ClientResponse.fromAggregate(client);
	}


	
	
	
}
