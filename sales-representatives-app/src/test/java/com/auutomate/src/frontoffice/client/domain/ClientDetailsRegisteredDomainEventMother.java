package com.auutomate.src.frontoffice.client.domain;

import com.auutomate.src.backoffice.client.domain.registar.ClientRegisteredDomainEvent;

public final class ClientDetailsRegisteredDomainEventMother {
	public static ClientRegisteredDomainEvent random() {
		return new ClientRegisteredDomainEvent(ClientIdMother.random().id(), ClientMailMother.random().mail(), ClientNameMother.random().name());
	}
}
