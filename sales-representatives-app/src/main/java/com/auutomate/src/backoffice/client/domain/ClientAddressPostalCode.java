package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

public final record ClientAddressPostalCode(Integer postalCode) {

	@Override
	public int hashCode() {
		return Objects.hash(postalCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientAddressPostalCode other = (ClientAddressPostalCode) obj;
		return Objects.equals(postalCode, other.postalCode);
	}

	@Override
	public String toString() {
		return postalCode.toString();
	}

}
