package com.auutomate.contexts.client_details.domain;

import com.auutomate.contexts.client_details.domain.registar.ClientDetailsRegistarDomainEvent;

public final class ClientDetailsSavedDomainEventMother {
	public static ClientDetailsRegistarDomainEvent create(String id, String mail, String name) {
		return new ClientDetailsRegistarDomainEvent(id, mail, name);
	}
}
