package com.auutomate.src.shared.aplication.find;

import com.auutomate.src.backoffice.client.domain.ClientId;
import com.auutomate.src.backoffice.product.domain.ProductReference;

public final class ProductNotFoundException extends Exception{
	public ProductNotFoundException(ProductReference reference) {
		super("Product with reference <" + reference.reference() + "> does not exist");
	}
}