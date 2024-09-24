package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

public final record ClientNID(String nid) {

	@Override
	public int hashCode() {
		return Objects.hash(nid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientNID other = (ClientNID) obj;
		return Objects.equals(nid, other.nid);
	}

	@Override
	public String toString() {
		return nid;
	}

}
