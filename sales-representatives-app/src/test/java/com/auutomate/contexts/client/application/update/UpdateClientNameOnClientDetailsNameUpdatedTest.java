package com.auutomate.contexts.client.application.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.contexts.client.application.find.ClientFinder;
import com.auutomate.contexts.client.application.save.ClientRegistar;
import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientIdMother;
import com.auutomate.contexts.client.domain.ClientMother;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public class UpdateClientNameOnClientDetailsNameUpdatedTest {
	private final String UPDATED_NAME_VALUE = "LOL";
	private Client client = this.givenARandomClient();
	private ClientRepository repo = this.givenAMockClientRepository();
	private ClientFinder finder = this.givenAClientFinder(repo);
	private ClientRegistar registar = this.givenAClientRegistar(repo);
	private ClientNameUpdatedDomainEvent event;
	private UpdateClientNameOnClientDetailsNameUpdated suscriber = this.givenAUpdateClientNameOnClientDetailsNameUpdated(finder, registar);
	
	@Test
	void it_should_update_existing_client_name() throws ClientNotFoundException {
		event = this.givenAClientNameUpdatedDomainEventWithUpdatedName(client.getId(), UPDATED_NAME_VALUE);
		this.mockClientRepoSearchResult(client);
		
		suscriber.on(event);
		
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		this.verifyClientRepoSavedIsBeingCalledOnce(captor);
		
		this.checkClientEquality(client, captor.getValue());
		this.checkIfClientNameHasBeenUpdate(UPDATED_NAME_VALUE, captor.getValue().getName());
		
	}
	
	@Test
	void it_should_not_update_client_name_when_same_case_content() throws ClientNotFoundException {
		event = this.givenAClientNameUpdatedDomainEventWithUpdatedName(client.getId(), client.getName());
		this.mockClientRepoSearchResult(client);
		
		suscriber.on(event);
		
		this.verifyClientRepoSearchIsBeingCalledOnce(ClientIdMother.create(client.getId()));
		
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		this.verifyClientRepoSavedIsNotBeingCalled(captor);
		
	}
	
	private Client givenARandomClient() {
		return ClientMother.random();
	}
	
	private ClientRepository givenAMockClientRepository() {
		return Mockito.mock(ClientRepository.class);
	}
	
	private ClientFinder givenAClientFinder(ClientRepository repo) {
		return new ClientFinder(repo);
	}
	
	private ClientRegistar givenAClientRegistar(ClientRepository repo) {
		return new ClientRegistar(repo);
	}
	
	private ClientNameUpdatedDomainEvent givenAClientNameUpdatedDomainEventWithUpdatedName(String id, String name) {
		return new ClientNameUpdatedDomainEvent(id, name);
	}
	
	private UpdateClientNameOnClientDetailsNameUpdated givenAUpdateClientNameOnClientDetailsNameUpdated(ClientFinder finder, ClientRegistar registar) {
		return new UpdateClientNameOnClientDetailsNameUpdated(finder, registar);
		
	}
	
	private void mockClientRepoSearchResult(Client client) {
		when(repo.search(ClientIdMother.create(client.getId()))).thenReturn(Optional.ofNullable(client));
	}
	
	private void checkClientEquality(Client expected, Client actual) {
		assertEquals(expected, actual);
	}

	private void checkIfClientNameHasBeenUpdate(String expected, String actual) {
		assertEquals(expected, actual);
	}
	
	private void verifyClientRepoSavedIsBeingCalledOnce(ArgumentCaptor<Client> captor) {
		verify(repo).save(captor.capture());
	}

	private void verifyClientRepoSavedIsNotBeingCalled(ArgumentCaptor<Client> captor) {
		verify(repo, never()).save(captor.capture());
	}
	
	private void verifyClientRepoSearchIsBeingCalledOnce(ClientId id) {
		verify(repo, times(1)).search(id);
	}
	
}
