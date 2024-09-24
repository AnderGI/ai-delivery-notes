package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

public final record ClientBankAccount(String account) {

	@Override
	public int hashCode() {
		return Objects.hash(account);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientBankAccount other = (ClientBankAccount) obj;
		return Objects.equals(account, other.account);
	}

	@Override
	public String toString() {
		return account;
	}

}
