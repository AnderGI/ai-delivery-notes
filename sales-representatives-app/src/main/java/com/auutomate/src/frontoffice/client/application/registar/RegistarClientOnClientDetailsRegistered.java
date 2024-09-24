package com.auutomate.src.frontoffice.client.application.registar;

import java.util.Arrays;
import java.util.List;

import com.auutomate.src.backoffice.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;
import com.auutomate.src.shared.domain.DomainEvent;
import com.auutomate.src.shared.domain.EventListener;

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
