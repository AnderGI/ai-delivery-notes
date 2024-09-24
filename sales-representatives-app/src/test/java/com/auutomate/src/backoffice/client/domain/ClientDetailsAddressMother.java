package com.auutomate.src.backoffice.client.domain;

import com.auutomate.src.backoffice.client_details.domain.ClientDetailsAddress;

public abstract class ClientDetailsAddressMother {

    // Método abstracto para generar una dirección aleatoria
    public abstract ClientDetailsAddress randomAddress();

    // Método abstracto para crear una dirección específica con parámetros
    public abstract ClientDetailsAddress createAddress(String name, String population, Integer postalCode, String province);
}
