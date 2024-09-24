package com.auutomate.src.backoffice.client_details.application.find;

import com.auutomate.src.backoffice.client_details.domain.ClientDetails;
import com.auutomate.src.backoffice.client_details.domain.ClientDetailsRepository;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public final class ClientDetailsFinder {
	private ClientDetailsRepository repo;
	
	public ClientDetailsFinder(ClientDetailsRepository repo) {
		this.repo = repo;
	}
	
	public ClientDetails find(String id) throws ClientNotFoundException {
		return ClientDetails.find(repo, id);
	}
}
