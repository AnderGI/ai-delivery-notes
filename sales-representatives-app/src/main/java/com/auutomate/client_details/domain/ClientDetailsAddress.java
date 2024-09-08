package com.auutomate.client_details.domain;


public abstract class ClientDetailsAddress {
	private ClientDetailsAddressName name;
	private ClientDetailsAddressPopulation population;
	private ClientDetailsAddressProvince province;
	private ClientDetailsAddressPostalCode postalCode;
	public ClientDetailsAddress(String name, String population, Integer postalCode, String province) {
		this.name = new ClientDetailsAddressName(name);
		this.population = new ClientDetailsAddressPopulation(population);
		this.postalCode = new ClientDetailsAddressPostalCode(postalCode);
		this.province = new ClientDetailsAddressProvince(province);
	}
}
