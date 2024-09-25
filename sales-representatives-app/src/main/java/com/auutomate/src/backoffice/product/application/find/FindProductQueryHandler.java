package com.auutomate.src.backoffice.product.application.find;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.QueryHandler;

public class FindProductQueryHandler implements QueryHandler<FindProductQuery, ProductResponse, ProductNotFoundException> {

	private final ProductFinder finder;
	
	public FindProductQueryHandler(ProductFinder finder) {
		this.finder = finder;
	}
	
	@Override
	public ProductResponse handle(FindProductQuery query) throws ProductNotFoundException {
		Product product = finder.find(query.reference());
		return ProductResponse.fromProduct(product);
	}

}
