package com.auutomate.contexts.client_details.domain.registar;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetailsRegistar {
	public static void registar(EventBus bus, ClientDetailsRepository repo, ClientDetails clientDetails) throws Exception {
		repo.save(clientDetails);
		bus.publish(new ClientDetailsRegisteredDomainEvent(clientDetails.idValue(), clientDetails.mailValue(),
				clientDetails.nameValue()));
	}
}
