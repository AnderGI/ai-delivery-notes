package com.auutomate.contexts.shared.domain;

import java.util.Date;
import java.util.UUID;

public class DomainEvent {
	private final Date ocurredOn;
	private final String eventId;
	public DomainEvent() {
		ocurredOn = new Date();
		eventId = UUID.randomUUID().toString();
	}
	
	public String getOcurredOn() {
		return this.ocurredOn.toString();
	}
	
	public String getEventId() {
		return this.eventId;
	}
}
