package com.auutomate.src.backoffice.product.application.find;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.backoffice.product.domain.Products;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;

public final class ProductFinder {
	private final ProductRepository repo;
	
	public ProductFinder(ProductRepository repo) {
		this.repo = repo;
	}
	
	public Product find(String reference) throws ProductNotFoundException {
		return Product.search(repo, reference);
	}
	
}
