package com.auutomate.contexts.client_details.domain.registar;

import java.util.Arrays;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsBankAccount;
import com.auutomate.contexts.client_details.domain.ClientDetailsBillingAddress;
import com.auutomate.contexts.client_details.domain.ClientDetailsDeliveryAddress;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.ClientMail;
import com.auutomate.contexts.client_details.domain.ClientNID;
import com.auutomate.contexts.client_details.domain.ClientName;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetailsRegistar {
	public static void registar(EventBus bus, ClientDetailsRepository repo, ClientDetails clientDetails) {
		repo.save(clientDetails);
		bus.publish(new ClientDetailsRegisteredDomainEvent(clientDetails.idValue(), clientDetails.mailValue(),
				clientDetails.nameValue()));
	}
}
