package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

public final record ClientMail(String mail) {

	@Override
	public int hashCode() {
		return Objects.hash(mail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientMail other = (ClientMail) obj;
		return Objects.equals(mail, other.mail);
	}

	@Override
	public String toString() {
		return mail;
	}

}
