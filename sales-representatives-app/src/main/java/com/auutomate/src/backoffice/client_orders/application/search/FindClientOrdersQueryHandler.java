package com.auutomate.src.backoffice.client_orders.application.search;

import com.auutomate.src.backoffice.client_orders.domain.ClientOrders;
import com.auutomate.src.shared.domain.QueryHandler;

public final class FindClientOrdersQueryHandler implements QueryHandler<FindClientOrdersQuery, ClientOrders, Exception> {

	private final ClientOrdersSearcher searcher;

	public FindClientOrdersQueryHandler(ClientOrdersSearcher searcher) {
		this.searcher = searcher;
	}
	
	@Override
	public ClientOrders handle(FindClientOrdersQuery query) throws Exception {
		// TODO Auto-generated method stub
		return searcher.search(query.id());
	}

}
