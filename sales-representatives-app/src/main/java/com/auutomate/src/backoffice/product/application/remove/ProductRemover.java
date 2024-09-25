package com.auutomate.src.backoffice.product.application.remove;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class ProductRemover {
	private final EventBus bus;
	private final ProductRepository repo;
	
	public ProductRemover(EventBus bus, ProductRepository repo) {
		this.bus = bus;
		this.repo = repo;
	}
	
	public void remove(String reference) throws ProductNotFoundException {
		Product.remove(bus, repo, reference);
	}
	
}
