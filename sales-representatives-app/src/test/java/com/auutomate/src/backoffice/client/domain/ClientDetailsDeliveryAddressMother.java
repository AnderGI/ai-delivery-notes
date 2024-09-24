package com.auutomate.src.backoffice.client.domain;
import com.auutomate.src.backoffice.client.domain.ClientDeliveryAddress;
import com.github.javafaker.Faker;

public final class ClientDetailsDeliveryAddressMother extends ClientDetailsAddressMother {

    @Override
    public ClientDeliveryAddress randomAddress() {
        Faker faker = new Faker();
        return new ClientDeliveryAddress(faker.address().fullAddress(), faker.address().city(),
                Integer.valueOf(faker.regexify("(0[1-9]|[1-4][0-9]|5[0-2])\\d{3}")), faker.address().country());
    }

    @Override
    public ClientDeliveryAddress createAddress(String name, String population, Integer postalCode, String province) {
        return new ClientDeliveryAddress(name, population, postalCode, province);
    }
}
