package com.auutomate.contexts.client.domain;

import java.util.Random;

import com.auutomate.contexts.client_details.domain.ClientNID;

public final class ClientNIDMother {
	 private static final char[] letrasControl = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 
             'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

	public static ClientNID random() {
		return new ClientNID(validNid());
	}

	public static ClientNID create(String nid) {
		return new ClientNID(nid);
	}
	
	private static String validNid() {
		Random random = new Random();
        int numero = random.nextInt(90000000) + 10000000; 
        int resto = numero % 23;
        char letra = letrasControl[resto];
        String dni = numero + String.valueOf(letra);
        return dni;
	}
}
