package com.auutomate.src.backoffice.product.domain.remove;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductReference;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class ProductRemover {
	public static void remove(EventBus bus, ProductRepository repo, String reference) throws ProductNotFoundException  {
		ProductReference pReference = new ProductReference(reference);
		Product p = repo.search(pReference).orElse(null);
		if(p == null) throw new ProductNotFoundException(pReference);
		repo.remove(pReference);
		bus.publish(new ProductRemovedDomainEvent(pReference.reference()));
	}
}
