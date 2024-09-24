package com.auutomate.src.backoffice.client.domain.registar;

import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.shared.domain.EventBus;

public final class ClientRegistar {
	public static void registar(EventBus bus, ClientRepository repo, Client clientDetails) throws Exception {
		repo.save(clientDetails);
		bus.publish(new ClientRegisteredDomainEvent(clientDetails.idValue(), clientDetails.mailValue(),
				clientDetails.nameValue()));
	}
}
