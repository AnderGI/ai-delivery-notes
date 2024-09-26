package com.auutomate.src.backoffice.product.domain.update;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductReference;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class ProductDescriptionUpdater {
	public static void update(EventBus bus, ProductRepository repo, String reference, String description) throws ProductNotFoundException {
		ProductReference pReference = new ProductReference(reference);
		Product product = repo.search(pReference).orElse(null);
		if(product == null) throw new ProductNotFoundException(pReference);
		product.updateDescription(description);
		repo.save(product);
		bus.publish(ProductDescriptionUpdatedDomainEvent.fromProduct(product));
	}
}
