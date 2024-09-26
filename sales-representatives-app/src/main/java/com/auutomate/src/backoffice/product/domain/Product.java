package com.auutomate.src.backoffice.product.domain;

import java.util.Objects;

import com.auutomate.src.backoffice.product.domain.find.ProductFinder;
import com.auutomate.src.backoffice.product.domain.find.ProductsFinder;
import com.auutomate.src.backoffice.product.domain.remove.ProductRemover;
import com.auutomate.src.backoffice.product.domain.save.ProductSaver;
import com.auutomate.src.backoffice.product.domain.update.ProductDescriptionUpdater;
import com.auutomate.src.backoffice.product.domain.update.ProductPriceUpdater;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class Product {
	private final ProductReference reference;
	private ProductPrice price;
	private ProductDescription description;
	
	public Product(String referece, String price, String description) {
		this.reference = new ProductReference(referece);
		this.price = new ProductPrice(price);
		this.description = new ProductDescription(description);
	}
	
	private Product(ProductReference reference, ProductPrice price, ProductDescription description) {
		this.reference = reference;
		this.price = price;
		this.description = description;
	}
	
	public static void create(EventBus bus, ProductRepository repo,String reference, String price, String description) throws Exception {
		Product product = new Product(new ProductReference(reference), new ProductPrice(price), new ProductDescription(description));
		ProductSaver.save(bus, repo, product);
	}
	
	
	public static Product search(ProductRepository repo, String reference) throws ProductNotFoundException {
		return ProductFinder.find(repo, reference);
	}
	
	public static Products searchAll(ProductRepository repo){
		return ProductsFinder.findAll(repo);
	}
	
	public static void remove(EventBus bus, ProductRepository repo, String reference) throws ProductNotFoundException {
		ProductRemover.remove(bus, repo, reference);
	}

	public static void updatePrice(EventBus bus, ProductRepository repo, String reference, String price) throws ProductNotFoundException {
		ProductPriceUpdater.update(bus, repo, reference, price);
	}
	
	public static void updateDescription(EventBus bus, ProductRepository repo, String reference, String description) throws ProductNotFoundException {
		ProductDescriptionUpdater.update(bus, repo, reference, description);
	}
	
	public String reference() {
		return this.reference.reference();
	}
	
	public String description() {
		return this.description.description();
	}
	
	public String price() {
		return this.price.price();
	}
	
	public void updateDescription(String description) {
		this.description = new ProductDescription(description);
	}

	public void updatePrice(String price) {
		this.price = new ProductPrice(price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(reference, other.reference);
	}

	
	
}
