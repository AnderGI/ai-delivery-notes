package com.auutomate.contexts.client_details.domain;

import com.auutomate.contexts.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;

public final class ClientDetailsSavedDomainEventMother {
	public static ClientDetailsRegisteredDomainEvent create(String id, String mail, String name) {
		return new ClientDetailsRegisteredDomainEvent(id, mail, name);
	}
}
