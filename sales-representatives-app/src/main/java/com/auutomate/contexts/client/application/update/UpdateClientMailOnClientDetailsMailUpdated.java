package com.auutomate.contexts.client.application.update;

import java.util.Arrays;
import java.util.List;

import com.auutomate.contexts.client.application.find.ClientFinder;
import com.auutomate.contexts.client.application.registar.ClientRegistar;
import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.update.ClientMailUpdatedDomainEvent;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.DomainEvent;
import com.auutomate.contexts.shared.domain.EventListener;

public class UpdateClientMailOnClientDetailsMailUpdated implements EventListener<ClientMailUpdatedDomainEvent>{

	private final ClientFinder finder;
	private final ClientMailUpdater registar;
	private final List<Class<? extends DomainEvent>> events = Arrays.asList(ClientMailUpdatedDomainEvent.class);
	
	public UpdateClientMailOnClientDetailsMailUpdated(ClientFinder finder, ClientMailUpdater registar) {
		this.finder = finder;
		this.registar = registar;
	}
	
	
	@Override
	public void on(ClientMailUpdatedDomainEvent event) throws ClientNotFoundException {
		registar.update(event.getClientId(), event.getClientMail());
	}

	@Override
	public List<Class<? extends DomainEvent>> subscribedTo() {
		return events;
	}

}
