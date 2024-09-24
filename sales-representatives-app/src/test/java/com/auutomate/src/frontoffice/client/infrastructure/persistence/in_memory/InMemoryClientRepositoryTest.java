package com.auutomate.src.frontoffice.client.infrastructure.persistence.in_memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.auutomate.src.backoffice.client.domain.ClientId;
import com.auutomate.src.backoffice.client.domain.update.ClientMailUpdatedDomainEvent;
import com.auutomate.src.backoffice.client.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.src.frontoffice.client.application.find.ClientFinder;
import com.auutomate.src.frontoffice.client.application.registar.ClientRegistar;
import com.auutomate.src.frontoffice.client.application.update.ClientMailUpdater;
import com.auutomate.src.frontoffice.client.application.update.ClientNameUpdater;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientIdMother;
import com.auutomate.src.frontoffice.client.domain.ClientMother;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.frontoffice.client.domain.Clients;
import com.auutomate.src.frontoffice.client.domain.ClientsMother;
import com.auutomate.src.frontoffice.client.infrastructure.persistence.in_memory.InMemoryClientRepository;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public class InMemoryClientRepositoryTest {
    private ClientRepository inmemoryRepo;
    private ClientFinder finder;
    private ClientRegistar registrar;
    private ClientMailUpdater mailUpdater;
    private ClientNameUpdater nameUpdater;

    @BeforeEach
    void setup() {
        inmemoryRepo = new InMemoryClientRepository();
        finder = new ClientFinder(inmemoryRepo);
        registrar = new ClientRegistar(inmemoryRepo);
        mailUpdater = new ClientMailUpdater(inmemoryRepo);
        nameUpdater = new ClientNameUpdater(inmemoryRepo);
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
    void it_should_update_existing_client_via_name_updater() throws ClientNotFoundException {
        Client client = givenARandomClient();
        registarNewClient(client);

        String newName = "Updated Name";
        updateClientName(client, newName);

        Client updatedClient = findClientById(client.getId());
        assertClientNameUpdated(updatedClient, newName);
    }

    @Test
    void it_should_update_existing_client_via_mail_updater() throws ClientNotFoundException {
        Client client = givenARandomClient();
        registarNewClient(client);

        String newMail = "updated@mail.com";
        updateClientMail(client, newMail);

        Client updatedClient = findClientById(client.getId());
        assertClientMailUpdated(updatedClient, newMail);
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

    private ClientMailUpdatedDomainEvent givenClientMailUpdatedEvent(Client client, String newMail) {
        return new ClientMailUpdatedDomainEvent(client.getId(), newMail);
    }

    private void registarNewClient(Client client) {
        registrar.registar(client.getId(), client.getName(), client.getMail());
    }

    private void registarMultipleClients(Clients clients) {
        clients.getAll().forEach(c -> registarNewClient(c));
    }

    private Client findClientById(String clientId) throws ClientNotFoundException {
        return finder.find(clientId);
    }

    private Clients findAllClients() {
        return finder.findAll();
    }

    private void updateClientName(Client client, String newName) throws ClientNotFoundException {
        nameUpdater.update(client.getId(), newName);
    }

    private void updateClientMail(Client client, String newMail) throws ClientNotFoundException {
        mailUpdater.update(client.getId(), newMail);
    }

    private void assertClientEquality(Client actual, Client expected) {
        assertEquals(expected, actual);
    }

    private void assertClientNameUpdated(Client client, String expectedName) {
        assertEquals(expectedName, client.getName());
    }

    private void assertClientMailUpdated(Client client, String expectedMail) {
        assertEquals(expectedMail, client.getMail());
    }

    private void assertClientsListEquality(Clients expected, Clients actual) {
        assertEquals(expected, actual);
    }

    private void assertClientsListIsEmpty(Clients clients) {
        assertEquals(0, clients.getAll().size());
    }
}
