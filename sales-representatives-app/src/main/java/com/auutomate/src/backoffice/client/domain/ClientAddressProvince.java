package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

public final record ClientAddressProvince(String province) {

	@Override
	public int hashCode() {
		return Objects.hash(province);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientAddressProvince other = (ClientAddressProvince) obj;
		return Objects.equals(province, other.province);
	}

	@Override
	public String toString() {
		return province;
	}

}
