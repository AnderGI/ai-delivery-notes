package com.auutomate.src.backoffice.product.application.find;

import com.auutomate.src.backoffice.product.domain.Products;
import com.auutomate.src.shared.domain.QueryHandler;

public final class FindAllProductsQueryHandler implements QueryHandler<FindAllProductsQuery, ProductsResponse, Exception> {

	private final ProductsFinder finder;
	
	public FindAllProductsQueryHandler(ProductsFinder finder) {
		this.finder = finder;
	}
	
	@Override
	public ProductsResponse handle(FindAllProductsQuery query) throws Exception {
		Products products = finder.find();
		return ProductsResponse.fromProducts(products);
	}

}
