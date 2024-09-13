package com.auutomate.contexts.client_details.domain;

public abstract class ClientDetailsAddressMother {

    // Método abstracto para generar una dirección aleatoria
    public abstract ClientDetailsAddress randomAddress();

    // Método abstracto para crear una dirección específica con parámetros
    public abstract ClientDetailsAddress createAddress(String name, String population, Integer postalCode, String province);
}
