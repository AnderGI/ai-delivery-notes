package com.auutomate.src.backoffice.product.application.find;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.backoffice.product.domain.Products;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;


public final class ProductsFinder {
	private final ProductRepository repo;
	
	public ProductsFinder(ProductRepository repo) {
		this.repo = repo;
	}
	
	public Products find(){
		return Product.searchAll(repo);
	}
	
}