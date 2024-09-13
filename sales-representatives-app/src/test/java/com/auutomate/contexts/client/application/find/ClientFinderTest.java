package com.auutomate.contexts.client.application.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientMother;
import com.auutomate.contexts.client.domain.ClientRepository;

public class ClientFinderTest {
    
    @Test
    void it_should_return_empty_list_with_empty_repository() {
        // Mock del repositorio de clients
        ClientRepository repo = this.givenAMockClientRepository();
        ClientFinder finder = givenAClientFinder(repo);

        // Simular que el repositorio devuelve una lista vacía
        when(repo.searchAll()).thenReturn(Optional.of(Collections.emptyList()));

        // Verificar que el resultado es una lista vacía
        List<Client> result = finder.findAll();
        assertNotNull(result);
        assertEquals(0, result.size());

        // Verificar que el repositorio fue llamado exactamente una vez
        verify(repo, times(1)).searchAll();
    }

    @Test
    void it_should_return_a_list_of_clients() {
        // Mock del repositorio de clients
        ClientRepository repo = this.givenAMockClientRepository();
        ClientFinder finder = givenAClientFinder(repo);

        // Crear una lista de clientes de prueba usando el Object Mother
        List<Client> clients = List.of(ClientMother.random(), ClientMother.random());

        // Simular que el repositorio devuelve esta lista
        when(repo.searchAll()).thenReturn(Optional.of(clients));

        // Verificar que el resultado es la lista esperada
        List<Client> result = finder.findAll();
        assertNotNull(result);
        assertEquals(clients.size(), result.size());
        assertEquals(clients, result);

        // Verificar que el repositorio fue llamado exactamente una vez
        verify(repo, times(1)).searchAll();
    }
    
    private ClientRepository givenAMockClientRepository() {
        return Mockito.mock(ClientRepository.class);
    }
    
    private ClientFinder givenAClientFinder(ClientRepository repo) {
        return new ClientFinder(repo);
    }
}
