package com.auutomate.src.backoffice.product.application.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductMother;
import com.auutomate.src.backoffice.product.domain.ProductReference;
import com.auutomate.src.backoffice.product.domain.ProductReferenceMother;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.backoffice.product.domain.Products;
import com.auutomate.src.backoffice.product.domain.ProductsMother;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.Response;

public class ProductFinderTest {
	private Product product;
	private ProductRepository repo;
	private ProductFinder finder;
	private FindProductQuery singleQuery;
	private FindProductQueryHandler singleHandler;
	
	@BeforeEach
	void setup() {
		product = ProductMother.random();
		repo = Mockito.mock(ProductRepository.class);
		finder = new ProductFinder(repo);
		singleQuery = new FindProductQuery(product.reference());
		singleHandler = new FindProductQueryHandler(finder);
	}
	
	@Test
	void it_should_find_an_existing_product_by_reference() throws ProductNotFoundException {
		this.specifyProductSearchReturn(ProductReferenceMother.create(product.reference()),Optional.ofNullable(product));
		ProductResponse response = this.callHandlerSingleFind();
		this.verifyRepoSearchCall();
		this.assertProductEquality(product, response);
	}
	
	@Test
	void it_should_throw_exception_when_product_does_not_exist(){
		this.specifyProductSearchReturn(ProductReferenceMother.create(product.reference()),Optional.ofNullable(null));
		assertThrows(ProductNotFoundException.class, () -> this.callHandlerSingleFind());
		this.verifyRepoSearchCall();
	}

	private void specifyProductSearchReturn(ProductReference reference, Optional<Product> productOptional) {
		when(repo.search(reference)).thenReturn(productOptional);
	}
	
	private ProductResponse callHandlerSingleFind() throws ProductNotFoundException {
		return singleHandler.handle(singleQuery);
	}
	
	private void verifyRepoSearchCall() {
		ArgumentCaptor<ProductReference> repoCaptor = ArgumentCaptor.forClass(ProductReference.class);
		verify(repo, times(1)).search(repoCaptor.capture());
		assertEquals(ProductReferenceMother.create(product.reference()), repoCaptor.getValue());
	}
	
	private void assertProductEquality(Product product, ProductResponse response) {
		assertEquals(product.reference(), response.reference());
		assertEquals(product.description(), response.description());
		assertEquals(product.price(), response.price());
	}
	
}
