package com.auutomate.src.backoffice.client_details.domain;

import java.util.Objects;

import com.auutomate.src.backoffice.client_details.domain.find.ClientDetailsFinder;
import com.auutomate.src.backoffice.client_details.domain.registar.ClientDetailsRegistar;
import com.auutomate.src.backoffice.client_details.domain.update.ClientDetailsMailUpdater;
import com.auutomate.src.backoffice.client_details.domain.update.ClientDetailsNameUpdater;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class ClientDetails {
	private ClientId id;
	private ClientName name;
	private ClientMail mail;

	public ClientDetails(String id, String name, String mail) {
		this.id = new ClientId(id);
		this.name = new ClientName(name);
		this.mail = new ClientMail(mail);
}

	private ClientDetails(ClientId id, ClientName name, ClientMail mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	// Create
	public static void create(EventBus bus, ClientDetailsRepository repo, String id, String name,
			String mail)
			throws Exception {
		ClientDetails clientDetails = new ClientDetails(new ClientId(id), new ClientName(name), new ClientMail(mail));
		ClientDetailsRegistar.registar(bus, repo, clientDetails);

	}

	// find
	public static ClientDetails find(ClientDetailsRepository repo, String id) throws ClientNotFoundException {
		return ClientDetailsFinder.find(repo, id);
	}

	public static void updateName(ClientDetailsRepository repo, EventBus bus, String id, String name) throws Exception {
		ClientDetailsNameUpdater.update(repo, bus, id, name);
	}
	
	public static void updateMail(ClientDetailsRepository repo, EventBus bus, String id, String mail) throws Exception {
		ClientDetailsMailUpdater.update(repo, bus, id, mail);
	}	
	
	
	public String idValue() {
		return this.id.id();
	}

	public String nameValue() {
		return this.name.name();
	}

	public void updateName(String name) {
		this.name = new ClientName(name);
	}


	public String mailValue() {
		return this.mail.mail();
	}

	public void updateMail(String mail) {
		this.mail = new ClientMail(mail);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDetails other = (ClientDetails) obj;
		return Objects.equals(id, other.id);
	}

}
