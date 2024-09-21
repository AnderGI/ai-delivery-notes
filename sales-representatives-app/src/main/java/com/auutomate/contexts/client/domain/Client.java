package com.auutomate.contexts.client.domain;

import java.util.Objects;

import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.ClientNID;
import com.auutomate.contexts.client_details.domain.ClientName;

// Proyeccion
public final class Client {
	private final String id;
	private final String name;
	private final String mail; 

	public static Client fromPrimitives(String id, String name, String mail) {
		return new Client(id, name, mail);
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
