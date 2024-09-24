package com.auutomate.src.backoffice.product.application.save;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.shared.domain.EventBus;

public final class ProductSaver {
	private final ProductRepository repo;
	private final EventBus bus;
	
	public ProductSaver(ProductRepository repo, EventBus bus) {
		this.repo = repo;
		this.bus = bus;
	}
	
	public void save(String reference, String price, String description) throws Exception {
		Product.create(bus, repo, reference, price, description);
	}
	
}
