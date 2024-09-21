package com.auutomate.contexts.shared.infrastructure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auutomate.contexts.client.application.find.ClientFinder;
import com.auutomate.contexts.client.application.save.ClientRegistar;
import com.auutomate.contexts.client.application.save.RegistarClientOnClientDetailsRegistered;
import com.auutomate.contexts.client.application.update.UpdateClientMailOnClientDetailsMailUpdated;
import com.auutomate.contexts.client.application.update.UpdateClientNameOnClientDetailsNameUpdated;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client.infrastructure.persistence.in_memory.InMemoryClientRepository;
import com.auutomate.contexts.shared.domain.DomainEvent;
import com.auutomate.contexts.shared.domain.EventBus;
import com.auutomate.contexts.shared.domain.EventListener;

public class InMemoryEventBus implements EventBus<DomainEvent> {
	private Map<String, List<EventListener<? extends DomainEvent>>> map = new HashMap<>();
	private ClientRepository clientRepo = new InMemoryClientRepository();

	public InMemoryEventBus() {
		this.map.put("agi.client_details.event.registered.1", Arrays.asList(new RegistarClientOnClientDetailsRegistered(new ClientRegistar(clientRepo))));
		this.map.put("agi.client_details.event.mail_updated.1", Arrays.asList(new UpdateClientMailOnClientDetailsMailUpdated(new ClientFinder(clientRepo),new ClientRegistar(clientRepo))));
		this.map.put("agi.client_details.event.name_updated.1", Arrays.asList(new UpdateClientNameOnClientDetailsNameUpdated(new ClientFinder(clientRepo),new ClientRegistar(clientRepo))));
				
	}

	@Override
	public void publish(DomainEvent event) throws Exception {
	    List<EventListener<? extends DomainEvent>> subscribers = map.get(event.getEventName());
	    
	    if (subscribers != null) {
	        for (EventListener<? extends DomainEvent> listener : subscribers) {
	            listener.on(event);
	        }
	    }
		
	}
}
