package com.auutomate.contexts.client_details.application.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.contexts.client.domain.ClientIdMother;
import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsObjectMother;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.update.ClientMailUpdatedDomainEvent;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.EventBus;

public class ClientDetailsMailUpdaterTest {
	private ClientDetails client;
	private ClientDetailsRepository repo;
	private EventBus bus;
	private ClientDetailsMailUpdater updater;
	private UpdateClientDetailsMailCommandHandler handler;
	private final String newMail = "NEW_MAIL";
	
	@BeforeEach
	void setup() {
		client = ClientDetailsObjectMother.random();
		repo = Mockito.mock(ClientDetailsRepository.class);
		bus = Mockito.mock(EventBus.class);
		updater = new ClientDetailsMailUpdater(repo, bus);
		handler = new UpdateClientDetailsMailCommandHandler(updater);
	}
	
	
	@Test
	void it_should_update_client_name_when_new_mail() throws Exception {		
		this.mockRepoSearchForExitingClient();
		this.updateClientMail(newMail);
		this.verifyNumberOfRepoSaveCalls(1);
		this.assertClientEquality();
		this.verifyNumberOfBusPubishCalls(1);
		this.assertEventEquality();
	}
	
	@Test
	void it_should_not_update_client_name_when_same_mail() throws Exception {
		this.mockRepoSearchForExitingClient();
		this.updateClientMail(client.mailValue());
		this.verifyNumberOfRepoSaveCalls(0);
		this.verifyNumberOfBusPubishCalls(0);
	}
	
	@Test
	void it_should_throw_an_exception_when_non_existing_client() throws Exception {
		this.mockRepoSearchForNonExitingClient();
		this.assertClientNotFoundExceptionThrown();
		
	}
	
	private void mockRepoSearchForExitingClient() {
		when(repo.search(ClientIdMother.create(client.idValue()))).thenReturn(Optional.ofNullable(client));
	}
	
	private void mockRepoSearchForNonExitingClient() {
		when(repo.search(ClientIdMother.create(client.idValue()))).thenReturn(Optional.ofNullable(null));
	}

	private void assertClientNotFoundExceptionThrown() {
		assertThrows(ClientNotFoundException.class, () -> updater.update(client.idValue(), client.mailValue()));
	}
	
	private void verifyNumberOfRepoSaveCalls(int n) {
		ArgumentCaptor<ClientDetails> details = ArgumentCaptor.forClass(ClientDetails.class);
		verify(repo, times(n)).save(details.capture());
	}

	private void verifyNumberOfBusPubishCalls(int n) throws Exception {
		ArgumentCaptor<ClientMailUpdatedDomainEvent> event = ArgumentCaptor.forClass(ClientMailUpdatedDomainEvent.class);
		verify(bus, times(n)).publish(event.capture());
	}
	
	private void assertClientEquality() {
		ArgumentCaptor<ClientDetails> details = ArgumentCaptor.forClass(ClientDetails.class);
		verify(repo).save(details.capture());
		assertEquals(client, details.getValue());
	}
	
	private void updateClientMail(String newName) throws Exception {
		handler.handle(new UpdateClientDetailsMailCommand(client.idValue(), newName));
	}
	
	private void assertEventEquality() throws Exception {
		ArgumentCaptor<ClientMailUpdatedDomainEvent> event = ArgumentCaptor.forClass(ClientMailUpdatedDomainEvent.class);
		verify(bus).publish(event.capture());
		ClientMailUpdatedDomainEvent expectedEvent = new ClientMailUpdatedDomainEvent(client.idValue(), newMail);
		assertEquals(expectedEvent.getClientId(), event.getValue().getClientId());
		assertEquals(expectedEvent.getClientMail(), event.getValue().getClientMail());
	}
}
