package com.auutomate.src.backoffice.product.domain.update;

import java.util.Objects;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.save.ProductSavedDomainEvent;
import com.auutomate.src.shared.domain.DomainEvent;

public final class ProductPriceUpdatedDomainEvent extends DomainEvent{
	
	private final String EVENT_NAME = "agi.backoffice.product.event.product_price_updated.1";
	private final String reference;
	private final String price;
	
	private ProductPriceUpdatedDomainEvent(String reference, String price) {
		this.reference = reference;
		this.price = price;
	}
	
	public static ProductPriceUpdatedDomainEvent fromProduct(Product product) {
		return new ProductPriceUpdatedDomainEvent(product.reference(), product.price());
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
	@Override
	public int hashCode() {
		return Objects.hash(price, reference);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPriceUpdatedDomainEvent other = (ProductPriceUpdatedDomainEvent) obj;
		return Objects.equals(price, other.price)
				&& Objects.equals(reference, other.reference);
	}

}
