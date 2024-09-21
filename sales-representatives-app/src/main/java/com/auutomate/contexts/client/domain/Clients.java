package com.auutomate.contexts.client.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Clients {
	private final List<Client> clientsDDBB;
	
	private Clients(List<Client> clients) {
		this.clientsDDBB = clients;
	}
	
	
	public static Clients empty() {
		return new Clients(new ArrayList<>());
	}
	
	public static Clients fromValues(Client... clients) {
		return new Clients(Arrays.asList(clients));
	}
	
	public void add(Client c) {
		clientsDDBB.add(c);
	}
	
	public List<Client> getAll(){
		return clientsDDBB;
	}

	public Integer indexOf(Client c) {
		return clientsDDBB.indexOf(c);
	}
	
	public void setClientAtIndex(Client c, Integer at) {
		clientsDDBB.set(at, c);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientsDDBB);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clients other = (Clients) obj;
		return Objects.equals(clientsDDBB, other.clientsDDBB);
	}
	
	
	
}
