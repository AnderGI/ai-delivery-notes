package com.auutomate.contexts.client_details.domain;

import com.auutomate.contexts.client.domain.ClientId;
import com.auutomate.contexts.client.domain.ClientIdMother;
import com.auutomate.contexts.client.domain.ClientMailMother;
import com.auutomate.contexts.client.domain.ClientNIDMother;
import com.auutomate.contexts.client.domain.ClientName;
import com.auutomate.contexts.client.domain.ClientNameMother;

public final class ClientDetailsObjectMother {
	public static ClientDetails random() {
		return new ClientDetails(
				ClientIdMother.random().id(),
				ClientNameMother.random().name(),
				ClientNIDMother.random().nid(),
				ClientMailMother.random().mail(),
				ClientDetailsBankAccountMother.random().account(),
				new ClientDetailsBillingAddressMother().randomAddress().getName(),
				new ClientDetailsBillingAddressMother().randomAddress().getPopulation(),
				new ClientDetailsBillingAddressMother().randomAddress().getPostalCode(),
				new ClientDetailsBillingAddressMother().randomAddress().getProvince(),
				new ClientDetailsDeliveryAddressMother().randomAddress().getName(),
				new ClientDetailsDeliveryAddressMother().randomAddress().getPopulation(),
				new ClientDetailsDeliveryAddressMother().randomAddress().getPostalCode(),
				new ClientDetailsDeliveryAddressMother().randomAddress().getProvince()
		);
	}
	public static ClientDetails create(String id, String name, String nid, String mail, String bankAccount, 
			String bAddressName, String bPopulation, Integer bPostalCode, String bProvince,
			String dAddressName, String dPopulation, Integer dPostalCode, String dProvince) {
		return new ClientDetails(id, name, nid, mail, bankAccount, bAddressName, bPopulation, bPostalCode, bProvince, dAddressName, dPopulation, dPostalCode, dProvince);
	}
}
