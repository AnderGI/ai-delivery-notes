package com.auutomate.src.backoffice.product.domain.find;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductReference;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.backoffice.product.domain.Products;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;

public final class ProductFinder {
	public static Product find(ProductRepository repo, String reference) throws ProductNotFoundException {
		ProductReference pReference = new ProductReference(reference);
		Product p = repo.search(pReference).orElse(null);
		if(p == null) throw new ProductNotFoundException(pReference);
		return p;
	}
}
