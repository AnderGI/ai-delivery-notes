package com.auutomate.src.backoffice.client.application.find;
import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.find.ClientResponse;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.QueryHandler;

public class FindClientQueryHandler implements QueryHandler<FindClientQuery, ClientResponse, ClientNotFoundException> {

	private final ClientFinder finder;
	
	public FindClientQueryHandler(ClientFinder finder) {
		this.finder = finder;
	}
	
	@Override
	public ClientResponse handle(FindClientQuery query) throws ClientNotFoundException {
		Client client = finder.find(query.id());
		return ClientResponse.fromAggregate(client);
	}

}
