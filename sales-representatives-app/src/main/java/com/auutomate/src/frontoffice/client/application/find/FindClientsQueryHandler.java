package com.auutomate.src.frontoffice.client.application.find;

import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.QueryHandler;

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
