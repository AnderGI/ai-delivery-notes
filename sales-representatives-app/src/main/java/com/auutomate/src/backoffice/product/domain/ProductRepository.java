package com.auutomate.src.backoffice.product.domain;

import java.util.Optional;

public interface ProductRepository {
	void save(Product product);
	void remove(ProductReference reference);
	Products searchAll();
	Optional<Product> search(ProductReference reference);
}
