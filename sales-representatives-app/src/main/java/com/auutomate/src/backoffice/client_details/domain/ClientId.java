package com.auutomate.src.backoffice.client_details.domain;

import java.util.Objects;

public final record ClientId(String id) {

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientId other = (ClientId) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return id;
	}

}
