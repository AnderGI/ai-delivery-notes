package com.auutomate.contexts.client.domain;

import java.util.Objects;

import com.auutomate.contexts.client.domain.find.ClientFinder;
import com.auutomate.contexts.client.domain.registar.ClientRegistar;
import com.auutomate.contexts.client.domain.update.ClientMailUpdater;
import com.auutomate.contexts.client.domain.update.ClientNameUpdater;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.ClientNID;
import com.auutomate.contexts.client_details.domain.ClientName;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

// Proyeccion
public final class Client {
	private final String id;
	private String name;
	private String mail; 

	public static Client fromPrimitives(String id, String name, String mail) {
		return new Client(id, name, mail);
	}
	
	
	public static void updateMail(ClientRepository repo,String id, String mail) throws ClientNotFoundException {
		ClientMailUpdater.update(repo, id, mail);
	}
	
	public static void updateName(ClientRepository repo,String id, String name) throws ClientNotFoundException {
		ClientNameUpdater.update(repo, id, name);
	}

	public static Clients findAll(ClientRepository repo){
		return ClientFinder.findAll(repo);
	}
	
	public static Client find(ClientRepository repo,String id) throws ClientNotFoundException {
		return ClientFinder.find(repo, id);
	}
	
	public static void registar(ClientRepository repo, String id, String name, String mail) {
		ClientRegistar.registar(repo, id, name, mail);
	}
	
	private Client(String id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public void updateMail(String mail) {
		this.mail = mail;
	}
	
	public void updateName(String name) {
		this.name = name;
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
