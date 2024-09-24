package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

public final record ClientAddressPopulation(String name) {

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
		ClientAddressPopulation other = (ClientAddressPopulation) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}

}
