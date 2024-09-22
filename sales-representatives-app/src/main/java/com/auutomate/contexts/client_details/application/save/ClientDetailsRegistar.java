package com.auutomate.contexts.client_details.application.save;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetailsRegistar {
	private final ClientDetailsRepository repo;
	private final EventBus eventBus;
	public ClientDetailsRegistar(ClientDetailsRepository repo, EventBus eventBus) {
		this.repo = repo;
		this.eventBus = eventBus;
	}
	
	public void registar(String id, String name, String nid, String mail, String bankAccount, 
			String bAddressName, String bPopulation, Integer bPostalCode, String bProvince,
			String dAddressName, String dPopulation, Integer dPostalCode, String dProvince) throws Exception {
		ClientDetails
				.create(eventBus, repo, id, name, nid, mail, bankAccount, bAddressName, bPopulation, bPostalCode, bProvince, dAddressName, dPopulation, dPostalCode, dProvince);
	}
}
