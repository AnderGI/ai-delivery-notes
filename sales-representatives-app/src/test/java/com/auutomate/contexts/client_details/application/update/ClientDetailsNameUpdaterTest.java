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
import org.mockito.Mock;
import org.mockito.Mockito;

import com.auutomate.contexts.client.domain.ClientIdMother;
import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsObjectMother;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.EventBus;

public class ClientDetailsNameUpdaterTest {
	private ClientDetails client;
	private ClientDetailsRepository repo;
	private EventBus bus;
	private ClientDetailsNameUpdater updater;
	private final String newName = "NEW_NAME";
	
	@BeforeEach
	void setup() {
		client = ClientDetailsObjectMother.random();
		repo = Mockito.mock(ClientDetailsRepository.class);
		bus = Mockito.mock(EventBus.class);
		updater = new ClientDetailsNameUpdater(repo, bus);
	}
	
	
	@Test
	void it_should_update_client_name_when_new_name() throws Exception {		
		this.mockRepoSearchForExitingClient();
		this.updateClientName(newName);
		this.verifyNumberOfRepoSaveCalls(1);
		this.assertClientEquality();
		this.verifyNumberOfBusPubishCalls(1);
		this.assertEventEquality();
	}
	
	@Test
	void it_should_not_update_client_name_when_same_name() throws Exception {
		this.mockRepoSearchForExitingClient();
		this.updateClientName(client.nameValue());
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
		assertThrows(ClientNotFoundException.class, () -> updater.update(client.idValue(), client.nameValue()));
	}
	
	private void verifyNumberOfRepoSaveCalls(int n) {
		ArgumentCaptor<ClientDetails> details = ArgumentCaptor.forClass(ClientDetails.class);
		verify(repo, times(n)).save(details.capture());
	}

	private void verifyNumberOfBusPubishCalls(int n) throws Exception {
		ArgumentCaptor<ClientNameUpdatedDomainEvent> event = ArgumentCaptor.forClass(ClientNameUpdatedDomainEvent.class);
		verify(bus, times(n)).publish(event.capture());
	}
	
	private void assertClientEquality() {
		ArgumentCaptor<ClientDetails> details = ArgumentCaptor.forClass(ClientDetails.class);
		verify(repo).save(details.capture());
		assertEquals(client, details.getValue());
	}
	
	private void updateClientName(String newName) throws Exception {
		updater.update(client.idValue(), newName);
	}
	
	private void assertEventEquality() throws Exception {
		ArgumentCaptor<ClientNameUpdatedDomainEvent> event = ArgumentCaptor.forClass(ClientNameUpdatedDomainEvent.class);
		verify(bus).publish(event.capture());
		ClientNameUpdatedDomainEvent expectedEvent = new ClientNameUpdatedDomainEvent(client.idValue(), newName);
		assertEquals(expectedEvent.getClientId(), event.getValue().getClientId());
		assertEquals(expectedEvent.getClientName(), event.getValue().getClientName());
	}
	
}
