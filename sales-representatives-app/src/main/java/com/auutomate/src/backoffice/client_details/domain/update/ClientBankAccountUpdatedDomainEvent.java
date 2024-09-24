package com.auutomate.src.backoffice.client_details.domain.update;

import com.auutomate.src.shared.domain.DomainEvent;

public final class ClientBankAccountUpdatedDomainEvent extends DomainEvent{
	private final String clientId;
	private final String bankAccount;
	public ClientBankAccountUpdatedDomainEvent(String id, String bankAccount) {
		this.clientId = id;
		this.bankAccount = bankAccount;
	}
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}
}
