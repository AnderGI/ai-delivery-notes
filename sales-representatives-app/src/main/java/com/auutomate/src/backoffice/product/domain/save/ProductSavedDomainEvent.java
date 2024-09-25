package com.auutomate.src.backoffice.product.domain.save;

import java.util.Objects;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.shared.domain.DomainEvent;

public final class ProductSavedDomainEvent extends DomainEvent{
	
	private final String EVENT_NAME = "agi.backoffice.product.event.product_saved.1";
	private final String reference;
	private final String price;
	private final String description;
	
	private ProductSavedDomainEvent(String reference, String price, String description) {
		this.reference = reference;
		this.price = price;
		this.description = description;
	}
	
	public static ProductSavedDomainEvent fromProduct(Product product) {
		return new ProductSavedDomainEvent(product.reference(), product.price(), product.description());
	}
	
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return EVENT_NAME;
	}
	
	public String reference() {
		return reference;
	}
	
	public String price() {
		return price;
	}

	public String description() {
		return description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, price, reference);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSavedDomainEvent other = (ProductSavedDomainEvent) obj;
		return Objects.equals(description, other.description) && Objects.equals(price, other.price)
				&& Objects.equals(reference, other.reference);
	}	
	
	
}
