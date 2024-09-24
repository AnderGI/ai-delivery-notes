package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client.domain.registar.ClientRegisteredDomainEvent;

public final class ClientDetailsSavedDomainEventMother {
	public static ClientRegisteredDomainEvent create(String id, String mail, String name) {
		return new ClientRegisteredDomainEvent(id, mail, name);
	}
}
