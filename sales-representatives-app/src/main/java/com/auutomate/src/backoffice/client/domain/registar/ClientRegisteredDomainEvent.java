package com.auutomate.src.backoffice.client.domain.registar;

import java.util.Objects;

import com.auutomate.src.shared.domain.DomainEvent;

public final class ClientRegisteredDomainEvent extends DomainEvent{
	private final String id;
	private final String mail;
	private final String name;
	private final String eventName = "agi.client_details.event.registered.1";
	public ClientRegisteredDomainEvent(String id, String mail, String name) {
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
		ClientRegisteredDomainEvent other = (ClientRegisteredDomainEvent) obj;
		return Objects.equals(id, other.id) && Objects.equals(mail, other.mail) && Objects.equals(name, other.name);
	}
	
	
	
}
