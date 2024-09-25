package com.auutomate.src.backoffice.client.domain.update;

import com.auutomate.src.shared.domain.DomainEvent;

public final class ClientMailUpdatedDomainEvent extends DomainEvent {
	private final String clientId;
	private final String mail;
	private final String eventName = "agi.backoffice.client.event.mail_updated.1";
	public ClientMailUpdatedDomainEvent(String id, String mail) {
		this.clientId = id;
		this.mail = mail;
	}
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return this.eventName;
	}

	public String getClientId() {
		return this.clientId;
	}

	public String getClientMail() {
		return this.mail;
	}
	
}
