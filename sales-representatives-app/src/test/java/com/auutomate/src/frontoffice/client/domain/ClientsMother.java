package com.auutomate.src.frontoffice.client.domain;

import com.auutomate.src.frontoffice.client.domain.Clients;

public final class ClientsMother {
	public static Clients empty() {
		return Clients.empty();
	}
	
	public static Clients randomThree() {
		return Clients.fromValues(ClientMother.random(), ClientMother.random(), ClientMother.random());
	}
}
