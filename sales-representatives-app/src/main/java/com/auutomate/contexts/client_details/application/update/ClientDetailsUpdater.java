package com.auutomate.contexts.client_details.application.update;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetailsUpdater {
	private final ClientDetailsRepository repo;
	private final EventBus bus;
	public ClientDetailsUpdater(ClientDetailsRepository repo, EventBus bus) {
		this.repo = repo;
		this.bus = bus;
	}
	
	public void update(String idValue, String nameValue, String nidValue, String mailValue, String bankAccountValue,
			String billingAddressNameValue, String billingPopulationValue, Integer billingPostalCodeValue,
			String billingProvinceValue, String deliveryAddressNameValue, String deliveryPopulationValue,
			Integer deliveryPostalCodeValue, String deliveryProvinceValue) throws ClientNotFoundException {
		// TODO Auto-generated method stub
		ClientDetails.update(repo, bus, idValue, nameValue, nidValue, mailValue, bankAccountValue,
			billingAddressNameValue, billingPopulationValue, billingPostalCodeValue,
			billingProvinceValue, deliveryAddressNameValue, deliveryPopulationValue,
			deliveryPostalCodeValue, deliveryProvinceValue);
	}
	
	
}
