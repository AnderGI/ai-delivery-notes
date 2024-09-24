package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client.domain.ClientBillingAddress;
import com.github.javafaker.Faker;

public final class ClientDetailsBillingAddressMother extends ClientDetailsAddressMother {

    @Override
    public ClientBillingAddress randomAddress() {
        Faker faker = new Faker();
        return new ClientBillingAddress(faker.address().fullAddress(), faker.address().city(),
                Integer.valueOf(faker.regexify("(0[1-9]|[1-4][0-9]|5[0-2])\\d{3}")), faker.address().country());
    }

    @Override
    public ClientBillingAddress createAddress(String name, String population, Integer postalCode, String province) {
        return new ClientBillingAddress(name, population, postalCode, province);
    }
}
