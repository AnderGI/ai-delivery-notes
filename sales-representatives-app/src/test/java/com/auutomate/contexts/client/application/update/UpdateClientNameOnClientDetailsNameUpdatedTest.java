package com.auutomate.contexts.client.application.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
import com.auutomate.contexts.client_details.domain.update.ClientMailUpdatedDomainEvent;
import com.auutomate.contexts.client_details.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;

public class UpdateClientNameOnClientDetailsNameUpdatedTest {
	private final String UPDATED_NAME_VALUE = "LOL";
	private Client client = this.givenARandomClient();
	private ClientRepository repo = this.givenAMockClientRepository();
	private ClientFinder finder = this.givenAClientFinder(repo);
	private ClientNameUpdater nameUpdater = this.givenAClientNameUpdater(repo);
	private ClientNameUpdatedDomainEvent event;
	private UpdateClientNameOnClientDetailsNameUpdated suscriber = this.givenAUpdateClientNameOnClientDetailsNameUpdated(finder, nameUpdater);
	
	@BeforeEach
	void setup() {
		client = this.givenARandomClient();
		repo = this.givenAMockClientRepository();
		finder = this.givenAClientFinder(repo);
		nameUpdater = this.givenAClientNameUpdater(repo);
		suscriber = this.givenAUpdateClientNameOnClientDetailsNameUpdated(finder, nameUpdater);
	}
	
	
	@Test
	void it_should_update_existing_client_name() throws ClientNotFoundException {
		// Mock repo search
		this.mockClientRepoSearchResult();	
		// Call unit test entry point
		event = this.givenAClientNameUpdatedDomainEventWithUpdatedName(client.getId(), UPDATED_NAME_VALUE);
		suscriber.on(event);
		// Repo Search
		this.verifyClientRepoSearchIsBeingCalledOnce();
		// In search same client id
		this.verifyClientRepoSearchHasTheSameId();
		// Repo Saved
		this.verifyClientRepoSavedIsBeingCalledOnce();
		// In save same client´
		this.verifyClientRepoSaveHasTheSameClient();
		// Update Mail
		this.checkIfClientNameHasBeenUpdated();
	}
	
	@Test
	void it_should_not_update_client_mail_when_same_case_content() throws ClientNotFoundException {	
		// Mock Repo search
		this.mockClientRepoSearchResult();
		// Unit test entry point
		event = this.givenAClientNameUpdatedDomainEventWithUpdatedName(client.getId(), client.getName());
		suscriber.on(event);
		// Repo search
		this.verifyClientRepoSearchIsBeingCalledOnce();
		// In search same client id
		this.verifyClientRepoSearchHasTheSameId();
		// Save is not being called
		this.verifyClientRepoSavedIsNotBeingCalled();		
	}
	
	@Test
	void it_should_throw_an_exception_when_non_existing_client_name_tries_to_be_updated() {
		// Mock Repo search
		this.mockClientRepoSearchResultWithNull();
		// Unit test entry point
		event = this.givenAClientNameUpdatedDomainEventWithUpdatedName(client.getId(), client.getName());
		// Throws exception
		assertThrows(ClientNotFoundException.class, () -> suscriber.on(event)); 
		// Repo Search
		this.verifyClientRepoSearchIsBeingCalledOnce();
		// In search same client id
		this.verifyClientRepoSearchHasTheSameId();
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
	
	private ClientNameUpdater givenAClientNameUpdater(ClientRepository repo) {
		return new ClientNameUpdater(repo);
	}
	
	private ClientNameUpdatedDomainEvent givenAClientNameUpdatedDomainEventWithUpdatedName(String id, String name) {
		return new ClientNameUpdatedDomainEvent(id, name);
	}
	
	private UpdateClientNameOnClientDetailsNameUpdated givenAUpdateClientNameOnClientDetailsNameUpdated(ClientFinder finder, ClientNameUpdater updater) {
		return new UpdateClientNameOnClientDetailsNameUpdated(finder, updater);
		
	}
	
	private void mockClientRepoSearchResult() {
		when(repo.search(ClientIdMother.create(client.getId()))).thenReturn(Optional.ofNullable(client));
	}
	
	private void mockClientRepoSearchResultWithNull() {
		when(repo.search(ClientIdMother.create(client.getId()))).thenReturn(Optional.ofNullable(null));
	}

	private void verifyClientRepoSearchIsBeingCalledOnce() {
		verify(repo, times(1)).search(ClientIdMother.create(client.getId()));
	}
	
	private void verifyClientRepoSearchHasTheSameId() {
		ArgumentCaptor<ClientId> captor = ArgumentCaptor.forClass(ClientId.class);
		verify(repo).search(captor.capture());
		assertEquals(ClientIdMother.create(client.getId()), ClientIdMother.create(captor.getValue().id()));
	}
	
	private void verifyClientRepoSavedIsBeingCalledOnce() {
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		verify(repo).save(captor.capture());
	}
	
	private void verifyClientRepoSaveHasTheSameClient() {
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		verify(repo).save(captor.capture());
		assertEquals(client, captor.getValue());
	}
	
	private void checkIfClientNameHasBeenUpdated() {
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		verify(repo).save(captor.capture());
		assertEquals(UPDATED_NAME_VALUE, captor.getValue().getMail());
	}
	
	private void verifyClientRepoSavedIsNotBeingCalled() {
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		verify(repo, never()).save(captor.capture());
	}
	
}
