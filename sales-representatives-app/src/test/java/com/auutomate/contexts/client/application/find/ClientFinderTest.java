package com.auutomate.contexts.client.application.find;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.auutomate.contexts.client.domain.*;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public class ClientFinderTest {
    private ClientRepository repo;
    private ClientFinder finder;

    @BeforeEach
    void setup() {
        repo = givenMockClientRepository();
        finder = givenClientFinder(repo);
    }

    @Test
    void it_should_return_empty_list_with_empty_repository() {
        Clients emptyClients = givenEmptyClients();

        mockRepoSearchAllToReturn(emptyClients);

        Clients result = finder.findAll();

        verifyResultSizeAndContent(result, emptyClients);
        verifyRepoSearchAllCalledOnce();
    }

    @Test
    void it_should_return_a_list_of_clients() {
        Clients clients = givenRandomClients();

        mockRepoSearchAllToReturn(clients);

        Clients result = finder.findAll();

        verifyResultSizeAndContent(result, clients);
        verifyRepoSearchAllCalledOnce();
    }

    @Test
    void it_should_return_an_existing_client() throws ClientNotFoundException {
        Client client = givenRandomClient();
        ClientId id = ClientIdMother.create(client.getId());

        mockRepoSearchByIdToReturn(client, id);

        Client result = finder.find(id);

        verifyClientEquality(result, client);
        verifyRepoSearchByIdCalledOnce(id);
    }

    @Test
    void it_should_throw_an_exception_with_non_existing_client() {
        ClientId id = ClientIdMother.random();

        mockRepoSearchByIdToReturn(null, id);

        assertThrows(ClientNotFoundException.class, () -> finder.find(id));

        verifyRepoSearchByIdCalledOnce(id);
    }

    // Métodos de ayuda

    private void mockRepoSearchAllToReturn(Clients clients) {
        when(repo.searchAll()).thenReturn(Optional.of(clients));
    }

    private void mockRepoSearchByIdToReturn(Client client, ClientId id) {
        when(repo.search(id)).thenReturn(Optional.ofNullable(client));
    }

    private void verifyRepoSearchAllCalledOnce() {
        verify(repo, times(1)).searchAll();
    }

    private void verifyRepoSearchByIdCalledOnce(ClientId id) {
        verify(repo, times(1)).search(id);
    }

    private void verifyResultSizeAndContent(Clients actual, Clients expected) {
        assertNotNull(actual);
        assertEquals(expected.getAll().size(), actual.getAll().size());
        assertEquals(expected, actual);
    }

    private void verifyClientEquality(Client actual, Client expected) {
        assertEquals(expected, actual);
    }

    private ClientRepository givenMockClientRepository() {
        return Mockito.mock(ClientRepository.class);
    }

    private ClientFinder givenClientFinder(ClientRepository repo) {
        return new ClientFinder(repo);
    }

    private Clients givenRandomClients() {
        return ClientsMother.randomThree();
    }

    private Clients givenEmptyClients() {
        return ClientsMother.empty();
    }

    private Client givenRandomClient() {
        return ClientMother.random();
    }
}
