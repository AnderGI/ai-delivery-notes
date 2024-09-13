package com.auutomate.contexts.client_details.domain;

import java.util.Objects;

public final record ClientDetailsAddressName(String name) {

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDetailsAddressName other = (ClientDetailsAddressName) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}

}
