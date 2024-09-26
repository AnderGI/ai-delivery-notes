package com.auutomate.src.backoffice.product.domain.find;

import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.backoffice.product.domain.Products;

public final class ProductsFinder {
	public static Products findAll(ProductRepository repo) {
		Products p = repo.searchAll().orElse(null);
		if(p == null) return Products.empty();
		return p;
	}

}