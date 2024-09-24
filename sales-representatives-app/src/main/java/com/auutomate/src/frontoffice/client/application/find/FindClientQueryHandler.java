package com.auutomate.src.frontoffice.client.application.find;

import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.QueryHandler;

public final class FindClientQueryHandler implements QueryHandler<FindClientQuery, ClientResponse, ClientNotFoundException> {

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
