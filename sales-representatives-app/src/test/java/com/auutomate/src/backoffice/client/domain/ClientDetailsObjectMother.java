package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client_details.domain.ClientDetails;
import com.auutomate.src.frontoffice.client.domain.ClientIdMother;
import com.auutomate.src.frontoffice.client.domain.ClientMailMother;
import com.auutomate.src.frontoffice.client.domain.ClientNameMother;

public final class ClientDetailsObjectMother {
	public static ClientDetails random() {
		return new ClientDetails(
				ClientIdMother.random().id(),
				ClientNameMother.random().name(),
				ClientMailMother.random().mail()
		);
	}
	public static ClientDetails create(String id, String name, String mail) {
		return new ClientDetails(id, name, mail);
	}
}
