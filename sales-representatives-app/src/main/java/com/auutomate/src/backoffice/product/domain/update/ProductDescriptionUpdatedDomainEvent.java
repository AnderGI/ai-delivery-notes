package com.auutomate.src.backoffice.product.domain.update;

import java.util.Objects;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.shared.domain.DomainEvent;

public final class ProductDescriptionUpdatedDomainEvent extends DomainEvent{
	private final String EVENT_NAME = "agi.backoffice.product.event.product_description_updated.1";
	private final String reference;
	private final String description;
	
	private ProductDescriptionUpdatedDomainEvent(String reference, String description) {
		this.reference = reference;
		this.description = description;
	}
	
	public static ProductDescriptionUpdatedDomainEvent fromProduct(Product product) {
		return new ProductDescriptionUpdatedDomainEvent(product.reference(), product.description());
	}
	
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return EVENT_NAME;
	}
	
	public String reference() {
		return reference;
	}
	
	public String description() {
		return description;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, reference);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDescriptionUpdatedDomainEvent other = (ProductDescriptionUpdatedDomainEvent) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(reference, other.reference);
	}
}
