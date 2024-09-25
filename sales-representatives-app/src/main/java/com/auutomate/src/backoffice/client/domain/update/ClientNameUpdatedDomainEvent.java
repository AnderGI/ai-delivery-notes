package com.auutomate.src.backoffice.client.domain.update;

import com.auutomate.src.shared.domain.DomainEvent;


public final class ClientNameUpdatedDomainEvent extends  DomainEvent{
	private final String clientId;
	private final String name;
	private final String eventName = "agi.backoffice.client.event.name_updated.1";
	public ClientNameUpdatedDomainEvent(String id, String name) {
		this.clientId = id;
		this.name = name;
	}
	@Override
	public String getEventName() {
		return eventName;
	}

	
	public String getClientId() {
		return this.clientId;
	}
	
	public String getClientName() {
		return this.name;
	}	
	
	
	
	
	
}
