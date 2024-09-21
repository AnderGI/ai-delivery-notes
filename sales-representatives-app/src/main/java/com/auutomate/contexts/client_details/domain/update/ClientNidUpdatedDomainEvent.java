package com.auutomate.contexts.client_details.domain.update;

import com.auutomate.contexts.shared.domain.DomainEvent;

public final class ClientNidUpdatedDomainEvent extends DomainEvent{
	private final String clientId;
	private final String nid;
	public ClientNidUpdatedDomainEvent(String id, String nid) {
		this.clientId = id;
		this.nid = nid;
	}
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}
}
