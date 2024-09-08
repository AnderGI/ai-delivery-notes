package com.auutomate.client_details.domain;



import com.auutomate.contexts.client.domain.ClientId;
import com.auutomate.contexts.client.domain.ClientMail;
import com.auutomate.contexts.client.domain.ClientNID;
import com.auutomate.contexts.client.domain.ClientName;

public final class ClientDetails {
	private ClientId id;
	private ClientName name;
	private ClientNID nid; // dni
	private ClientMail mail;
	private ClientDetailsBankAccount bankAccount;
	private ClientDetailsAddress address;
}
