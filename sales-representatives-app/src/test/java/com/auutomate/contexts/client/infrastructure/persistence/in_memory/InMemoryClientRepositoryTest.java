package com.auutomate.contexts.client.infrastructure.persistence.in_memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.auutomate.contexts.client.application.find.ClientFinder;
import com.auutomate.contexts.client.application.registar.ClientRegistar;
import com.auutomate.contexts.client.application.update.UpdateClientNameOnClientDetailsNameUpdated;
import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientIdMother;
import com.auutomate.contexts.client.domain.ClientMother;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client.domain.Clients;
import com.auutomate.contexts.client.domain.ClientsMother;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public class InMemoryClientRepositoryTest {
	private ClientRepository inmemoryRepo;
    private ClientFinder finder;
    private ClientRegistar registrar;

    @BeforeEach
    void setup() {
        inmemoryRepo = new InMemoryClientRepository();
        finder = new ClientFinder(inmemoryRepo);
        registrar = new ClientRegistar(inmemoryRepo);
    }

    @Test
    void it_should_save_a_client() {
        Client client = givenARandomClient();
        registarNewClient(client);
    }

    @Test
    void it_should_find_an_existing_client() throws ClientNotFoundException {
        Client client = givenARandomClient();
        registarNewClient(client);

        Client result = findClientById(client.getId());
        assertClientEquality(result, client);
    }

    @Test
    void it_should_return_all_clients() {
        Clients clients = givenRandomClients();
        registarMultipleClients(clients);

        Clients result = findAllClients();
        assertClientsListEquality(clients, result);
    }

    @Test
    void it_should_throw_exception_for_non_existing_client() {
        ClientId nonExistingClientId = givenANonExistingClientId();

        assertThrows(ClientNotFoundException.class, () -> {
            findClientById(nonExistingClientId.id());
        });
    }

    @Test
    void it_should_find_all_added_clients_individually() throws ClientNotFoundException {
        Clients clients = givenRandomClients();
        registarMultipleClients(clients);

        for (Client client : clients.getAll()) {
            Client foundClient = findClientById(client.getId());
            assertClientEquality(foundClient, client);
        }
    }

    @Test
    void it_should_return_empty_clients_list_when_no_clients_saved() {
        Clients result = findAllClients();
        assertClientsListIsEmpty(result);
    }

    @Test
    void it_should_update_existing_client_via_event() throws ClientNotFoundException {
        Client client = givenARandomClient();
        registarNewClient(client);

        String newName = "Updated Name";
        ClientNameUpdatedDomainEvent event = givenClientNameUpdatedEvent(client, newName);

        handleClientNameUpdatedEvent(event);

        Client updatedClient = findClientById(client.getId());
        assertClientNameUpdated(updatedClient, newName);
    }

    // ----------------- Helper Methods for Better Semantics -----------------

    private Client givenARandomClient() {
        return ClientMother.random();
    }

    private Clients givenRandomClients() {
        return ClientsMother.randomThree();
    }

    private ClientId givenANonExistingClientId() {
        return ClientIdMother.random();
    }

    private ClientNameUpdatedDomainEvent givenClientNameUpdatedEvent(Client client, String newName) {
        return new ClientNameUpdatedDomainEvent(client.getId(), newName);
    }

    private void registarNewClient(Client client) {
        registrar.registar(client.getId(), client.getName(), client.getMail());
    }

    private void registarMultipleClients(Clients clients) {
        clients.getAll().forEach(c -> registarNewClient(c));
    }

    private Client findClientById(String clientId) throws ClientNotFoundException {
        return finder.find(ClientIdMother.create(clientId));
    }

    private Clients findAllClients() {
        return finder.findAll();
    }

    private void handleClientNameUpdatedEvent(ClientNameUpdatedDomainEvent event) throws ClientNotFoundException {
        UpdateClientNameOnClientDetailsNameUpdated subscriber = new UpdateClientNameOnClientDetailsNameUpdated(finder, registrar);
        subscriber.on(event);
    }

    private void assertClientEquality(Client actual, Client expected) {
        assertEquals(expected, actual);
    }

    private void assertClientNameUpdated(Client client, String expectedName) {
        assertEquals(expectedName, client.getName());
    }

    private void assertClientsListEquality(Clients expected, Clients actual) {
        assertEquals(expected, actual);
    }

    private void assertClientsListIsEmpty(Clients clients) {
        assertEquals(0, clients.getAll().size());
    }


}
