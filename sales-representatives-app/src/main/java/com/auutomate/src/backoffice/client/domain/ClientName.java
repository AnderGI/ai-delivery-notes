package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

public final record ClientName(String name) {

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
		ClientName other = (ClientName) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}

}
