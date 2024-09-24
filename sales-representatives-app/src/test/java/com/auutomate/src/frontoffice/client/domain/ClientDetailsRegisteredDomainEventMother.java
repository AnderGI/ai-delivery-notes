package com.auutomate.src.frontoffice.client.domain;

import com.auutomate.src.backoffice.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;

public final class ClientDetailsRegisteredDomainEventMother {
	public static ClientDetailsRegisteredDomainEvent random() {
		return new ClientDetailsRegisteredDomainEvent(ClientIdMother.random().id(), ClientMailMother.random().mail(), ClientNameMother.random().name());
	}
}
