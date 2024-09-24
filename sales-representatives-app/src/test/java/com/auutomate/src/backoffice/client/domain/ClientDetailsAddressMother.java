package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client.domain.ClientAddress;

public abstract class ClientDetailsAddressMother {

    // Método abstracto para generar una dirección aleatoria
    public abstract ClientAddress randomAddress();

    // Método abstracto para crear una dirección específica con parámetros
    public abstract ClientAddress createAddress(String name, String population, Integer postalCode, String province);
}
