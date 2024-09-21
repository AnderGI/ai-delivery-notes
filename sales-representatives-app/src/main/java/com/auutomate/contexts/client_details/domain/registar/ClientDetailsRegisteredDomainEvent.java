package com.auutomate.contexts.client_details.domain.registar;

import java.util.Objects;

import com.auutomate.contexts.shared.domain.DomainEvent;

public final class ClientDetailsRegisteredDomainEvent extends DomainEvent{
	private final String id;
	private final String mail;
	private final String name;
	private final String eventName = "agi.client_details.event.registered.1";
	public ClientDetailsRegisteredDomainEvent(String id, String mail, String name) {
		super();
		this.id = id;
		this.mail = mail;
		this.name = name;
	}
	
	public String getClientId() {
		return this.id;
	}

	public String getClientMail() {
		return this.mail;
	}
	
	public String getClientName() {
		return this.name;
	}

	public String getEventName() {
		return this.eventName;
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
		ClientDetailsRegisteredDomainEvent other = (ClientDetailsRegisteredDomainEvent) obj;
		return Objects.equals(id, other.id) && Objects.equals(mail, other.mail) && Objects.equals(name, other.name);
	}
	
	
	
}
