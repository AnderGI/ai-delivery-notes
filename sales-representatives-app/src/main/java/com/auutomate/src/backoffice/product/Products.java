package com.auutomate.src.backoffice.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.Clients;

public final class Products {
	private final List<Product> productsDDBB;
	
	private Products(List<Product> products) {
		this.productsDDBB= products;
	}
	
	
	public static Products empty() {
		return new Products(new ArrayList<>());
	}
	
	public static Products fromValues(Product... products) {
		return new Products(Arrays.asList(products));
	}
	
	public void add(Product product) {
		productsDDBB.add(product);
	}
	
	public List<Product> getAll(){
		return productsDDBB;
	}

	public Integer indexOf(Product product) {
		return productsDDBB.indexOf(product);
	}
	
	public void setProductAtIndex(Product product, Integer at) {
		productsDDBB.set(at, product);
	}
}
