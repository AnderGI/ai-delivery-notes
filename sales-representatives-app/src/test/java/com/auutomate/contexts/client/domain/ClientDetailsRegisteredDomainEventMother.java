package com.auutomate.contexts.client.domain;

import com.auutomate.contexts.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;

public final class ClientDetailsRegisteredDomainEventMother {
	public static ClientDetailsRegisteredDomainEvent random() {
		return new ClientDetailsRegisteredDomainEvent(ClientIdMother.random().id(), ClientMailMother.random().mail(), ClientNameMother.random().name());
	}
}
