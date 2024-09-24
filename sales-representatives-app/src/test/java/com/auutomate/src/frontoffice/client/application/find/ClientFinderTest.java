package com.auutomate.src.frontoffice.client.application.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.src.backoffice.client.domain.ClientId;
import com.auutomate.src.frontoffice.client.application.find.ClientFinder;
import com.auutomate.src.frontoffice.client.application.find.ClientResponse;
import com.auutomate.src.frontoffice.client.application.find.ClientsResponse;
import com.auutomate.src.frontoffice.client.application.find.FindClientQuery;
import com.auutomate.src.frontoffice.client.application.find.FindClientQueryHandler;
import com.auutomate.src.frontoffice.client.application.find.FindClientsQuery;
import com.auutomate.src.frontoffice.client.application.find.FindClientsQueryHandler;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientIdMother;
import com.auutomate.src.frontoffice.client.domain.ClientMother;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.frontoffice.client.domain.Clients;
import com.auutomate.src.frontoffice.client.domain.ClientsMother;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public class ClientFinderTest {
	private FindClientQueryHandler findClientHandler;
	private FindClientsQueryHandler findClientsHandler;
	private ClientRepository repo;
	private ClientFinder finder;

	@BeforeEach
	void setup() {
		repo = givenMockClientRepository();
		finder = givenClientFinder(repo);
		findClientHandler = givenAFindClientQueryHandler(finder);
		findClientsHandler = givenAFindClientsQueryHandler(finder);
	}


	@Test
	void it_should_return_empty_list_with_empty_repository() throws Exception {
		Clients emptyClients = givenEmptyClients();

		mockRepoSearchAllToReturn(emptyClients);

		ClientsResponse result = callFindClientsHandler();

		verifyClientsAndClientsResponseSizeAndContent(emptyClients, result);
		verifyRepoSearchAllCalledOnce();
	}

	@Test
	void it_should_return_a_list_of_clients() throws Exception {
		Clients clients = givenRandomClients();

		mockRepoSearchAllToReturn(clients);

		ClientsResponse result = callFindClientsHandler();

		verifyClientsAndClientsResponseSizeAndContent(clients, result);
		verifyRepoSearchAllCalledOnce();
	}

	@Test
	void it_should_return_an_existing_client() throws ClientNotFoundException {
		Client client = givenRandomClient();
		ClientId id = ClientIdMother.create(client.getId());

		mockRepoSearchByIdToReturn(client, id);

		ClientResponse clientResponse = callFindClientHandler(id.id());
		
		verifyClientAndClientResponseEquality(client, clientResponse);
		verifySameRepoSearchId(id);
		verifyRepoSearchByIdCalledOnce(id);
	}

	@Test
	void it_should_throw_an_exception_with_non_existing_client() {
		ClientId id = ClientIdMother.random();

		mockRepoSearchByIdToReturnNull(id);

		assertThrows(ClientNotFoundException.class, () -> callFindClientHandler(id.id()));
		verifySameRepoSearchId(id);
		verifyRepoSearchByIdCalledOnce(id);
	}
	
	private void verifySameRepoSearchId(ClientId id) {
		ArgumentCaptor<ClientId> clientFinderCaptor = ArgumentCaptor.forClass(ClientId.class);
		verify(repo).search(clientFinderCaptor.capture());
		assertEquals(id, clientFinderCaptor.getValue());
	}

	private ClientResponse callFindClientHandler(String id) throws ClientNotFoundException {
		return findClientHandler.handle(new FindClientQuery(id));
	}

	private ClientsResponse callFindClientsHandler() throws Exception{
		return findClientsHandler.handle(new FindClientsQuery());
	}
	
	private void verifyClientAndClientResponseEquality(Client client, ClientResponse clientResponse) {
		assertEquals(client.getId(), clientResponse.id());
		assertEquals(client.getName(), clientResponse.name());
		assertEquals(client.getMail(), clientResponse.mail());
	}

	private void mockRepoSearchAllToReturn(Clients clients) {
        when(repo.searchAll()).thenReturn(Optional.of(clients));
    }

	private void mockRepoSearchByIdToReturn(Client client, ClientId id) {
        when(repo.search(id)).thenReturn(Optional.ofNullable(client));
    }

	private void mockRepoSearchByIdToReturnNull(ClientId id) {
        when(repo.search(id)).thenReturn(Optional.ofNullable(null));
    }
	
	private void verifyRepoSearchAllCalledOnce() {
		verify(repo, times(1)).searchAll();
	}

	private void verifyRepoSearchByIdCalledOnce(ClientId id) {
		verify(repo, times(1)).search(id);
	}

	private void verifyClientsAndClientsResponseSizeAndContent(Clients expected, ClientsResponse actual) {
		assertNotNull(actual.clients());
		assertEquals(expected.getAll().size(), actual.clients().size());
		assertEquals(expected.getAll(), actual.clients());
	}

	private void verifyClientEquality(Client actual, Client expected) {
		assertEquals(expected, actual);
	}

	private ClientRepository givenMockClientRepository() {
		return Mockito.mock(ClientRepository.class);
	}

	private FindClientQueryHandler givenAFindClientQueryHandler(ClientFinder finder) {
		return new FindClientQueryHandler(finder);
	}

	private FindClientsQueryHandler givenAFindClientsQueryHandler(ClientFinder finder) {
		return new FindClientsQueryHandler(finder);
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
