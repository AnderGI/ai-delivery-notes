package com.auutomate.src.backoffice.product.domain;

public interface ProductRepository {
	void save(Product product);
	Products searchAll();
	Product search(ProductReference reference);
}
