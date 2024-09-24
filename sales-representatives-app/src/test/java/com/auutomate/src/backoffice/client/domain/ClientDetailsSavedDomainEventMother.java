package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;

public final class ClientDetailsSavedDomainEventMother {
	public static ClientDetailsRegisteredDomainEvent create(String id, String mail, String name) {
		return new ClientDetailsRegisteredDomainEvent(id, mail, name);
	}
}
