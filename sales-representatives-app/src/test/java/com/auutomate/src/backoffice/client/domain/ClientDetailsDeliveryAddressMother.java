package com.auutomate.src.backoffice.client.domain;
import com.auutomate.src.backoffice.client_details.domain.ClientDetailsDeliveryAddress;
import com.github.javafaker.Faker;

public final class ClientDetailsDeliveryAddressMother extends ClientDetailsAddressMother {

    @Override
    public ClientDetailsDeliveryAddress randomAddress() {
        Faker faker = new Faker();
        return new ClientDetailsDeliveryAddress(faker.address().fullAddress(), faker.address().city(),
                Integer.valueOf(faker.regexify("(0[1-9]|[1-4][0-9]|5[0-2])\\d{3}")), faker.address().country());
    }

    @Override
    public ClientDetailsDeliveryAddress createAddress(String name, String population, Integer postalCode, String province) {
        return new ClientDetailsDeliveryAddress(name, population, postalCode, province);
    }
}
