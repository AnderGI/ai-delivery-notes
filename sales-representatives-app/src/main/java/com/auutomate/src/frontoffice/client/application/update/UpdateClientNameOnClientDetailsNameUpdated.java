package com.auutomate.src.frontoffice.client.application.update;

import java.util.Arrays;
import java.util.List;

import com.auutomate.src.backoffice.client.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.src.frontoffice.client.application.find.ClientFinder;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.DomainEvent;
import com.auutomate.src.shared.domain.EventListener;

public final class UpdateClientNameOnClientDetailsNameUpdated implements EventListener<ClientNameUpdatedDomainEvent>{

	private final ClientFinder finder;
	private final ClientNameUpdater registar;
	private final List<Class<? extends DomainEvent>> events = Arrays.asList(ClientNameUpdatedDomainEvent.class);
	
	public UpdateClientNameOnClientDetailsNameUpdated(ClientFinder finder, ClientNameUpdater registar) {
		this.finder = finder;
		this.registar = registar;
	}
	
	
	@Override
	public void on(ClientNameUpdatedDomainEvent event) throws ClientNotFoundException {
		registar.update(event.getClientId(), event.getClientName());
	}

	@Override
	public List<Class<? extends DomainEvent>> subscribedTo() {
		return events;
	}

	private boolean isClientNameEqualToEventName(Client find, ClientNameUpdatedDomainEvent event) {
		return find.getName().equals(event.getClientName());
	}
	
}
