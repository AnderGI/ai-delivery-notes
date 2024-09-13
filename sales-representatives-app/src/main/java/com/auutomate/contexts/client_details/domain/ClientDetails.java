package com.auutomate.contexts.client_details.domain;



import java.util.Objects;

import com.auutomate.contexts.client.domain.ClientId;
import com.auutomate.contexts.client.domain.ClientMail;
import com.auutomate.contexts.client.domain.ClientNID;
import com.auutomate.contexts.client.domain.ClientName;
import com.auutomate.contexts.client_details.domain.find.ClientDetailsFinder;
import com.auutomate.contexts.client_details.domain.find.ClientNotFoundException;
import com.auutomate.contexts.client_details.domain.registar.ClientDetailsRegistar;
import com.auutomate.contexts.shared.domain.EventBus;

public final class ClientDetails {
	private ClientId id;
	private ClientName name;
	private ClientNID nid; // dni
	private ClientMail mail;
	private ClientDetailsBankAccount bankAccount;
	private ClientDetailsBillingAddress billingAddress;
	private ClientDetailsDeliveryAddress deliveryAddress;
	
	public ClientDetails(String id, String name, String nid, String mail, String bankAccount, 
			String bAddressName, String bPopulation, Integer bPostalCode, String bProvince,
			String dAddressName, String dPopulation, Integer dPostalCode, String dProvince) {
		this.id = new ClientId(id);
		this.name = new ClientName(name);
		this.nid = new ClientNID(nid);
		this.mail = new ClientMail(mail);
		this.bankAccount = new ClientDetailsBankAccount(bankAccount);
		this.billingAddress = new ClientDetailsBillingAddress(bAddressName, bPopulation, bPostalCode, bProvince);
		this.deliveryAddress = new ClientDetailsDeliveryAddress(dAddressName, dPopulation, dPostalCode, dProvince);
	}
	
	private ClientDetails(ClientId id, ClientName name, ClientNID nid,
			ClientMail mail, ClientDetailsBankAccount account, 
			ClientDetailsBillingAddress bAddress,
			ClientDetailsDeliveryAddress dAddress) {
		this.id = id;
		this.name = name;
		this.nid = nid;
		this.mail = mail;
		this.bankAccount = bankAccount;
		this.billingAddress = bAddress;
		this.deliveryAddress = dAddress;
	}
	
	// Create
	public static void create(EventBus bus, ClientDetailsRepository repo,
			String id, String name, String nid, String mail, String bankAccount, 
			String bAddressName, String bPopulation, Integer bPostalCode, String bProvince,
			String dAddressName, String dPopulation, Integer dPostalCode, String dProvince) {
		ClientDetails clientDetails = new ClientDetails(
			new ClientId(id), new ClientName(name), new ClientNID(nid), new ClientMail(mail), 
			new ClientDetailsBankAccount(bankAccount),
			new ClientDetailsBillingAddress(bAddressName, bPopulation, bPostalCode, bProvince),
			new ClientDetailsDeliveryAddress(dAddressName, dPopulation, dPostalCode, dProvince)
		);
		ClientDetailsRegistar.registar(bus, repo, clientDetails);
		
	}
	
	// find
	public static ClientDetails find(ClientDetailsRepository repo, String id) throws ClientNotFoundException {
		return ClientDetailsFinder.find(repo, id);
	}
	
	
	public String idValue() {
	    return this.id.id();
	}

	public String nameValue() {
	    return this.name.name();
	}

	public String nidValue() {
	    return this.nid.nid();
	}

	public String mailValue() {
	    return this.mail.mail();
	}

	public String bankAccountValue() {
	    return this.bankAccount.account();
	}

	public String billingAddressNameValue() {
	    return this.billingAddress.getName();
	}

	public String billingPopulationValue() {
	    return this.billingAddress.getPopulation();
	}

	public Integer billingPostalCodeValue() {
	    return this.billingAddress.getPostalCode();
	}

	public String billingProvinceValue() {
	    return this.billingAddress.getProvince();
	}

	public String deliveryAddressNameValue() {
	    return this.deliveryAddress.getName();
	}

	public String deliveryPopulationValue() {
	    return this.deliveryAddress.getPopulation();
	}

	public Integer deliveryPostalCodeValue() {
	    return this.deliveryAddress.getPostalCode();
	}

	public String deliveryProvinceValue() {
	    return this.deliveryAddress.getProvince();
	}

	@Override
	public int hashCode() {
		return Objects.hash(bankAccount, billingAddress, deliveryAddress, id, mail, name, nid);
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
		return Objects.equals(bankAccount, other.bankAccount) && Objects.equals(billingAddress, other.billingAddress)
				&& Objects.equals(deliveryAddress, other.deliveryAddress) && Objects.equals(id, other.id)
				&& Objects.equals(mail, other.mail) && Objects.equals(name, other.name)
				&& Objects.equals(nid, other.nid);
	}




	
	

	
}
