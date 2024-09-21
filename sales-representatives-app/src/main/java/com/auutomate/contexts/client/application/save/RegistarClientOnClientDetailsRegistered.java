package com.auutomate.contexts.client.application.save;

import java.util.Arrays;
import java.util.List;

import com.auutomate.contexts.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;
import com.auutomate.contexts.shared.domain.DomainEvent;
import com.auutomate.contexts.shared.domain.EventListener;

public class RegistarClientOnClientDetailsRegistered implements EventListener<ClientDetailsRegisteredDomainEvent> {

	private final ClientRegistar registar;
	private final List<Class<? extends DomainEvent>> events = Arrays.asList(ClientDetailsRegisteredDomainEvent.class);
	
	public RegistarClientOnClientDetailsRegistered(ClientRegistar registar) {
		this.registar = registar;
	}
	@Override
	public void on(ClientDetailsRegisteredDomainEvent event) {
		// TODO Auto-generated method stub
		registar.registar(event.getClientId(), event.getClientName(), event.getClientMail());
	}
	@Override
	public List<Class<? extends DomainEvent>> subscribedTo() {
		// TODO Auto-generated method stub
		return events;
	}

}
