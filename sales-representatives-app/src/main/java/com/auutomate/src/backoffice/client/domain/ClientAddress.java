package com.auutomate.src.backoffice.client.domain;

import java.util.Objects;

public abstract class ClientAddress {
	private ClientAddressName name;
	private ClientAddressPopulation population;
	private ClientAddressProvince province;
	private ClientAddressPostalCode postalCode;
	public ClientAddress(String name, String population, Integer postalCode, String province) {
		this.name = new ClientAddressName(name);
		this.population = new ClientAddressPopulation(population);
		this.postalCode = new ClientAddressPostalCode(postalCode);
		this.province = new ClientAddressProvince(province);
	}
	public String getName() {
		return name.name();
	}
	public String getPopulation() {
		return population.name();
	}
	public String getProvince() {
		return province.province();
	}
	public Integer getPostalCode() {
		return postalCode.postalCode();
	}
	@Override
	public String toString() {
		return "ClientDetailsAddress [name=" + name + ", population=" + population + ", province=" + province
				+ ", postalCode=" + postalCode + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, population, postalCode, province);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientAddress other = (ClientAddress) obj;
		return Objects.equals(name, other.name) && Objects.equals(population, other.population)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(province, other.province);
	}
	
	
	
}
