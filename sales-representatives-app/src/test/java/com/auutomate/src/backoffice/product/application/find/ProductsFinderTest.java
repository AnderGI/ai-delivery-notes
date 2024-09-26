package com.auutomate.src.backoffice.product.application.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductMother;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.backoffice.product.domain.Products;
import com.auutomate.src.backoffice.product.domain.ProductsMother;

public class ProductsFinderTest {
	private Products products;
	private ProductRepository repo;
	private ProductsFinder finder;
	private FindAllProductsQuery allQuery;
	private FindAllProductsQueryHandler allHandler;
	
	@BeforeEach
	void setup() {
		repo = Mockito.mock(ProductRepository.class);
		finder = new ProductsFinder(repo);
		allQuery = new FindAllProductsQuery();
		allHandler = new FindAllProductsQueryHandler(finder);
	}
	

	@Test
	void it_should_find_all_products_when_repo_is_not_empty() throws Exception{
		products = this.givenProductsFromValues(ProductMother.random(), ProductMother.random(), ProductMother.random());
		this.specifyProductSearchAllReturn(Optional.ofNullable(products));
		ProductsResponse response = this.callHandlerAllFind();
		this.verifyRepoSearchAllCall();
		this.assertProductsEquality(products, response);
	}
	

	@Test
	void it_should_return_empty_products_when_repo_is_empty() throws Exception{
		products = this.givenEmptyProducts();
		this.specifyProductSearchAllReturn(Optional.ofNullable(products));
		ProductsResponse response = this.callHandlerAllFind();
		this.verifyRepoSearchAllCall();
		this.assertProductsEquality(products, response);
	}
	
	private void specifyProductSearchAllReturn(Optional<Products> productsOptional) {
		when(repo.searchAll()).thenReturn(productsOptional);
	}
	
	private ProductsResponse callHandlerAllFind() throws Exception {
		return allHandler.handle(allQuery);
	}
	
	private void verifyRepoSearchAllCall() {
		verify(repo, times(1)).searchAll();
	}
	
	private void assertProductsEquality(Products products, ProductsResponse response) {
		assertEquals(products.getAll(), response.products().getAll());
	}
	
	private Products givenEmptyProducts() {
		return ProductsMother.createEmpty();
	}

	private Products givenProductsFromValues(Product... products) {
		return ProductsMother.createFromValues(products);
	}
	
}
