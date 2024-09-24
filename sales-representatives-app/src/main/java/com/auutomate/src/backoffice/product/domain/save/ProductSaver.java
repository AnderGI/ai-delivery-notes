package com.auutomate.src.backoffice.product.domain.save;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.shared.domain.EventBus;

public final class ProductSaver {
	public static void save(EventBus bus, ProductRepository repo, Product product) throws Exception {
		repo.save(product);
		bus.publish(ProductSavedDomainEvent.fromProduct(product));
	}
}
