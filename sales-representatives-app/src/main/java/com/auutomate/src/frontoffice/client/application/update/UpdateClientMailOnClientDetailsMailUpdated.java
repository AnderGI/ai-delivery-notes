package com.auutomate.src.frontoffice.client.application.update;

import java.util.Arrays;
import java.util.List;

import com.auutomate.src.backoffice.client.domain.update.ClientMailUpdatedDomainEvent;
import com.auutomate.src.frontoffice.client.application.find.ClientFinder;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.DomainEvent;
import com.auutomate.src.shared.domain.EventListener;

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
