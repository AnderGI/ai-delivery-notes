package com.auutomate.src.shared.infrastructure.bus.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auutomate.src.frontoffice.client.application.find.ClientFinder;
import com.auutomate.src.frontoffice.client.application.registar.ClientRegistar;
import com.auutomate.src.frontoffice.client.application.registar.RegistarClientOnClientDetailsRegistered;
import com.auutomate.src.frontoffice.client.application.update.ClientMailUpdater;
import com.auutomate.src.frontoffice.client.application.update.ClientNameUpdater;
import com.auutomate.src.frontoffice.client.application.update.UpdateClientMailOnClientDetailsMailUpdated;
import com.auutomate.src.frontoffice.client.application.update.UpdateClientNameOnClientDetailsNameUpdated;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.frontoffice.client.infrastructure.persistence.in_memory.InMemoryClientRepository;
import com.auutomate.src.shared.domain.DomainEvent;
import com.auutomate.src.shared.domain.EventBus;
import com.auutomate.src.shared.domain.EventListener;

public class InMemoryEventBus implements EventBus<DomainEvent> {
	private Map<String, List<EventListener<? extends DomainEvent>>> map = new HashMap<>();
	private ClientRepository clientRepo = new InMemoryClientRepository();

	public InMemoryEventBus() {
		this.map.put("agi.backoffice.client.event.client_registered.1", Arrays.asList(new RegistarClientOnClientDetailsRegistered(new ClientRegistar(clientRepo))));
		this.map.put("agi.backoffice.client.event.mail_updated.1", Arrays.asList(new UpdateClientMailOnClientDetailsMailUpdated(new ClientFinder(clientRepo),new ClientMailUpdater(clientRepo))));
		this.map.put("agi.backoffice.client.event.name_updated.1", Arrays.asList(new UpdateClientNameOnClientDetailsNameUpdated(new ClientFinder(clientRepo),new ClientNameUpdater(clientRepo))));
		this.map.put("agi.backoffice.product.event.product_saved.1", null);
		this.map.put("agi.backoffice.product.event.product_removed.1", null);
	}

	@Override
	public void publish(DomainEvent event) {
	    List<? extends EventListener<? extends DomainEvent>> subscribers = map.get(event.getEventName());

	    if (subscribers != null) {
	        for (EventListener<? extends DomainEvent> listener : subscribers) {
	            // Hacer un cast seguro al tipo correcto
	            // @SuppressWarnings("unchecked")
	            EventListener<DomainEvent> castedListener = (EventListener<DomainEvent>) listener;
	            castedListener.on(event);  // Invocar el método on con el evento casteado
	        }
	    }
		
	}
}
