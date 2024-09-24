package com.auutomate.src.backoffice.product;

public final class Product {
	private final ProductReference reference;
	private ProductPrice price;
	private ProductDescription description;
	
	public Product(String referece, Double price, String description) {
		this.reference = new ProductReference(referece);
		this.price = new ProductPrice(price);
		this.description = new ProductDescription(description);
	}
	
	private Product(ProductReference reference, ProductPrice price, ProductDescription description) {
		this.reference = reference;
		this.price = price;
		this.description = description;
	}
	
	public static Product create(String reference, Double price, String description) {
		return  new Product(new ProductReference(reference), new ProductPrice(price), new ProductDescription(description));
	}
	
	public String reference() {
		return this.reference.reference();
	}
	
	public String description() {
		return this.description.description();
	}
	
	public Double price() {
		return this.price.price();
	}
	
	public void updateDescription(String description) {
		this.description = new ProductDescription(description);
	}

	public void updatePrice(Double price) {
		this.price = new ProductPrice(price);
	}

}
