package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

import com.auutomate.src.backoffice.client.domain.find.ClientFinder;
import com.auutomate.src.backoffice.client.domain.registar.ClientRegistar;
import com.auutomate.src.backoffice.client.domain.update.ClientMailUpdater;
import com.auutomate.src.backoffice.client.domain.update.ClientNameUpdater;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class Client {
	private ClientId id;
	private ClientName name;
	private ClientMail mail;

	public Client(String id, String name, String mail) {
		this.id = new ClientId(id);
		this.name = new ClientName(name);
		this.mail = new ClientMail(mail);
}

	private Client(ClientId id, ClientName name, ClientMail mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	// Create
	public static void create(EventBus bus, ClientRepository repo, String id, String name,
			String mail)
			throws Exception {
		Client clientDetails = new Client(new ClientId(id), new ClientName(name), new ClientMail(mail));
		ClientRegistar.registar(bus, repo, clientDetails);

	}

	// find
	public static Client find(ClientRepository repo, String id) throws ClientNotFoundException {
		return ClientFinder.find(repo, id);
	}

	public static void updateName(ClientRepository repo, EventBus bus, String id, String name) throws Exception {
		ClientNameUpdater.update(repo, bus, id, name);
	}
	
	public static void updateMail(ClientRepository repo, EventBus bus, String id, String mail) throws Exception {
		ClientMailUpdater.update(repo, bus, id, mail);
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
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

}
