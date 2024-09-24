package com.auutomate.contexts.client.application.find;

import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.QueryHandler;

public final class FindClientsQueryHandler implements QueryHandler<FindClientsQuery, ClientsResponse, ClientNotFoundException>{

	private final ClientFinder finder;
	
	public FindClientsQueryHandler(ClientFinder finder) {
		this.finder = finder;
	}
	
	@Override
	public ClientsResponse handle(FindClientsQuery query) throws ClientNotFoundException {
		return ClientsResponse.fromAggregate(finder.findAll());
	}

}
