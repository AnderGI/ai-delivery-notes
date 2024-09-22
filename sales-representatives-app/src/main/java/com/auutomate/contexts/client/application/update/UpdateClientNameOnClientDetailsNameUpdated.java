package com.auutomate.contexts.client.application.update;

import java.util.Arrays;
import java.util.List;

import com.auutomate.contexts.client.application.find.ClientFinder;
import com.auutomate.contexts.client.application.registar.ClientRegistar;
import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.DomainEvent;
import com.auutomate.contexts.shared.domain.EventListener;

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
