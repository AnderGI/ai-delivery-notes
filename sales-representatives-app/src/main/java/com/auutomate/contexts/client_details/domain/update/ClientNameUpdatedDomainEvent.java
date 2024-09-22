package com.auutomate.contexts.client_details.domain.update;

import java.util.Objects;

import com.auutomate.contexts.shared.domain.DomainEvent;

public final class ClientNameUpdatedDomainEvent extends  DomainEvent{
	private final String clientId;
	private final String name;
	private final String eventName = "agi.client_details.event.name_updated.1";
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
