package com.auutomate.src.frontoffice.client.application.update;

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

import com.auutomate.src.backoffice.client_details.domain.ClientId;
import com.auutomate.src.backoffice.client_details.domain.update.ClientMailUpdatedDomainEvent;
import com.auutomate.src.frontoffice.client.application.find.ClientFinder;
import com.auutomate.src.frontoffice.client.application.update.ClientMailUpdater;
import com.auutomate.src.frontoffice.client.application.update.UpdateClientMailOnClientDetailsMailUpdated;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientIdMother;
import com.auutomate.src.frontoffice.client.domain.ClientMother;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;

public class UpdateClientMailOnClientDetailsMailUpdatedTest {
	private final String UPDATED_MAIL_VALUE = "LOL";
	private Client client;
	private ClientRepository repo;
	private ClientFinder finder;
	private ClientMailUpdater registar;
	private ClientMailUpdatedDomainEvent event;
	private UpdateClientMailOnClientDetailsMailUpdated suscriber;
	
	
	@BeforeEach
	void setup() {
		client = this.givenARandomClient();
		repo = this.givenAMockClientRepository();
		finder = this.givenAClientFinder(repo);
		registar = this.givenAClientMailUpdater(repo);
		suscriber = this.givenAClientMailUpdatedDomainEventWithUpdatedMail(finder, registar);
	}
	
	@Test
	void it_should_update_existing_client_mail() throws ClientNotFoundException {
		// Mock repo search
		this.mockClientRepoSearchResult();	
		// Call unit test entry point
		event = this.givenAClientMailUpdatedDomainEventWithUpdatedMail(client.getId(), UPDATED_MAIL_VALUE);
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
		this.checkIfClientMailHasBeenUpdated();
	}
	
	@Test
	void it_should_not_update_client_mail_when_same_case_content() throws ClientNotFoundException {	
		// Mock Repo search
		this.mockClientRepoSearchResult();
		// Unit test entry point
		event = this.givenAClientMailUpdatedDomainEventWithUpdatedMail(client.getId(), client.getMail());
		suscriber.on(event);
		// Repo search
		this.verifyClientRepoSearchIsBeingCalledOnce();
		// In search same client id
		this.verifyClientRepoSearchHasTheSameId();
		// Save is not being called
		this.verifyClientRepoSavedIsNotBeingCalled();		
	}
	
	@Test
	void it_should_throw_an_exception_when_non_existing_client_mail_tries_to_be_updated() {
		// Mock Repo search
		this.mockClientRepoSearchResultWithNull();
		// Unit test entry point
		event = this.givenAClientMailUpdatedDomainEventWithUpdatedMail(client.getId(), client.getMail());
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
	
	private ClientMailUpdater givenAClientMailUpdater(ClientRepository repo) {
		return new ClientMailUpdater(repo);
	}
	
	private ClientMailUpdatedDomainEvent givenAClientMailUpdatedDomainEventWithUpdatedMail(String id, String mail) {
		return new ClientMailUpdatedDomainEvent(id, mail);
	}
	
	private UpdateClientMailOnClientDetailsMailUpdated givenAClientMailUpdatedDomainEventWithUpdatedMail(ClientFinder finder, ClientMailUpdater mailUpdater) {
		return new UpdateClientMailOnClientDetailsMailUpdated(finder, mailUpdater);
		
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
	
	private void verifyClientRepoSaveHasTheSameClient() {
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		verify(repo).save(captor.capture());
		assertEquals(client, captor.getValue());
	}

	private void checkIfClientMailHasBeenUpdated() {
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		verify(repo).save(captor.capture());
		assertEquals(UPDATED_MAIL_VALUE, captor.getValue().getMail());
	}
	
	private void verifyClientRepoSavedIsBeingCalledOnce() {
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		verify(repo).save(captor.capture());
	}

	private void verifyClientRepoSavedIsNotBeingCalled() {
		ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
		verify(repo, never()).save(captor.capture());
	}
	
	private void verifyClientRepoSearchHasTheSameId() {
		ArgumentCaptor<ClientId> captor = ArgumentCaptor.forClass(ClientId.class);
		verify(repo).search(captor.capture());
		assertEquals(ClientIdMother.create(client.getId()), ClientIdMother.create(captor.getValue().id()));
	}
	
}
