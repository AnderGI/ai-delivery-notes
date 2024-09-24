package com.auutomate.src.backoffice.client_details.domain;

import java.util.Objects;

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
		ClientDetailsAddress other = (ClientDetailsAddress) obj;
		return Objects.equals(name, other.name) && Objects.equals(population, other.population)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(province, other.province);
	}
	
	
	
}
