package com.auutomate.src.backoffice.product.domain;

import java.util.Objects;

public final record ProductReference(String reference) {

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
		ProductReference other = (ProductReference) obj;
		return Objects.equals(reference, other.reference);
	}

}
