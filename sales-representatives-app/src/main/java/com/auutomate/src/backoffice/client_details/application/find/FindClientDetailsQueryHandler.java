package com.auutomate.src.backoffice.client_details.application.find;
import com.auutomate.src.backoffice.client_details.domain.ClientDetails;
import com.auutomate.src.backoffice.client_details.domain.find.ClientDetailsResponse;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.QueryHandler;

public class FindClientDetailsQueryHandler implements QueryHandler<FindClientDetailsQuery, ClientDetailsResponse, ClientNotFoundException> {

	private final ClientDetailsFinder finder;
	
	public FindClientDetailsQueryHandler(ClientDetailsFinder finder) {
		this.finder = finder;
	}
	
	@Override
	public ClientDetailsResponse handle(FindClientDetailsQuery query) throws ClientNotFoundException {
		ClientDetails client = finder.find(query.id());
		return ClientDetailsResponse.fromAggregate(client);
	}

}
