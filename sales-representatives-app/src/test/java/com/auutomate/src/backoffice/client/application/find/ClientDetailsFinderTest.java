package com.auutomate.src.backoffice.client.application.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.src.backoffice.client.application.find.ClientFinder;
import com.auutomate.src.backoffice.client.application.find.FindClientQuery;
import com.auutomate.src.backoffice.client.application.find.FindClientQueryHandler;
import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientDetailsObjectMother;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.backoffice.client.domain.find.ClientResponse;
import com.auutomate.src.backoffice.client.domain.ClientId;
import com.auutomate.src.frontoffice.client.domain.ClientIdMother;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public class ClientDetailsFinderTest {
	private ClientRepository repo;
	private ClientFinder finder;
	private FindClientQueryHandler handler;
	
	@BeforeEach
	void setup() {
		repo = this.givenAClientDetailsRepoMock();
		finder = this.givenAClientFinder(repo);
		handler = new FindClientQueryHandler(finder);
	}
	
	@Test
	void it_should_find_an_existing_client() throws Exception {
		Client details = ClientDetailsObjectMother.random();
		ClientId id = ClientIdMother.create(details.idValue());
		this.mockClientDetailsSearchResult(id, Optional.ofNullable(details));
		ClientResponse response = handler.handle(new FindClientQuery(details.idValue()));
		// Repo search same id
		this.assertSearchCalledWithClientId(id);
		// Rep same client result
		this.assertClientDetailsEqualsResponse(details, response);
		// Repo is being called
		this.verifySearchIsBeingCalledOnce(id);
		
	}

	@Test
	void it_should_throw_an_exception_whit_non_existing_client(){
		ClientId id = ClientIdMother.random();
		// Mock repo search save method result
		this.mockClientDetailsSearchResult(id, Optional.ofNullable(null));
		// Not id -> exception
		this.assertClientNotFound(id);
		// Repo search called with same clientid
		this.assertSearchCalledWithClientId(id);
		// Repo search once called
		this.verifySearchIsBeingCalledOnce(id);
	}
	
	private void assertSearchCalledWithClientId(ClientId id) {
		ArgumentCaptor<ClientId> clientId = ArgumentCaptor.forClass(ClientId.class);
		verify(repo).search(clientId.capture());
		assertEquals(id, clientId.getValue());		
	}
	
	private void verifySearchIsBeingCalledOnce(ClientId id) {
		Mockito.verify(repo, times(1)).search(id);
	}

	private void assertClientNotFound(ClientId id) {
		assertThrows(ClientNotFoundException.class, () -> handler.handle(new FindClientQuery(id.id())));
	}
	
	private void assertClientDetailsEqualsResponse(Client client, ClientResponse response) {
		assertEquals(client.idValue(), response.id());
		assertEquals(client.nameValue(), response.name());
		assertEquals(client.mailValue(), response.mail());
	}
	
	private void mockClientDetailsSearchResult(ClientId id, Optional<Client> expectedResult) {
		Mockito.when(repo.search(id)).thenReturn(expectedResult);
	}

	private ClientRepository givenAClientDetailsRepoMock() {
		// TODO Auto-generated method stub
		return Mockito.mock(ClientRepository.class);
	}

	private ClientFinder givenAClientFinder(ClientRepository repo) {
		return new ClientFinder(repo);
	}
}
