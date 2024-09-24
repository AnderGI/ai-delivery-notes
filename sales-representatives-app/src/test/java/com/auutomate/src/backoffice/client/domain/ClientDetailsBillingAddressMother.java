package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client_details.domain.ClientDetailsBillingAddress;
import com.github.javafaker.Faker;

public final class ClientDetailsBillingAddressMother extends ClientDetailsAddressMother {

    @Override
    public ClientDetailsBillingAddress randomAddress() {
        Faker faker = new Faker();
        return new ClientDetailsBillingAddress(faker.address().fullAddress(), faker.address().city(),
                Integer.valueOf(faker.regexify("(0[1-9]|[1-4][0-9]|5[0-2])\\d{3}")), faker.address().country());
    }

    @Override
    public ClientDetailsBillingAddress createAddress(String name, String population, Integer postalCode, String province) {
        return new ClientDetailsBillingAddress(name, population, postalCode, province);
    }
}
