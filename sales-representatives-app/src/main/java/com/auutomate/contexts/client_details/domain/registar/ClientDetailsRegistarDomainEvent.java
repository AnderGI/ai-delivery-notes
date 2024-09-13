package com.auutomate.contexts.client_details.domain.registar;

import java.util.Objects;

import com.auutomate.contexts.shared.domain.DomainEvent;

public final class ClientDetailsRegistarDomainEvent extends DomainEvent{
	private final String id;
	private final String mail;
	private final String name;
	public ClientDetailsRegistarDomainEvent(String id, String mail, String name) {
		super();
		this.id = id;
		this.mail = mail;
		this.name = name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, mail, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDetailsRegistarDomainEvent other = (ClientDetailsRegistarDomainEvent) obj;
		return Objects.equals(id, other.id) && Objects.equals(mail, other.mail) && Objects.equals(name, other.name);
	}
	
	
	
}
