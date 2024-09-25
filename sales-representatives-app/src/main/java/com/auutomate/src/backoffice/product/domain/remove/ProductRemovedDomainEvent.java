package com.auutomate.src.backoffice.product.domain.remove;

import java.util.Objects;

import com.auutomate.src.shared.domain.DomainEvent;

public final class ProductRemovedDomainEvent extends DomainEvent{
	private final String EVENT_NAME = "agi.backoffice.product.event.product_removed.1";
	
	private final String reference;

	public ProductRemovedDomainEvent(String reference) {
		this.reference = reference;
	}
	
	public String reference() {
		return reference;
	}
	
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return EVENT_NAME;
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
		ProductRemovedDomainEvent other = (ProductRemovedDomainEvent) obj;
		return Objects.equals(reference, other.reference);
	}
	
	

}
