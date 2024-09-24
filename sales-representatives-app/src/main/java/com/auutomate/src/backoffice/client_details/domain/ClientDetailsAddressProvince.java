package com.auutomate.src.backoffice.client_details.domain;

import java.util.Objects;

public final record ClientDetailsAddressProvince(String province) {

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
		ClientDetailsAddressProvince other = (ClientDetailsAddressProvince) obj;
		return Objects.equals(province, other.province);
	}

	@Override
	public String toString() {
		return province;
	}

}
