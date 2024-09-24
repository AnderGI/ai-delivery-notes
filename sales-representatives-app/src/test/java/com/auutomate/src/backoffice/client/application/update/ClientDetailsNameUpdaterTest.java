package com.auutomate.src.backoffice.client.application.update;

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

import com.auutomate.src.backoffice.client.application.update.ClientNameUpdater;
import com.auutomate.src.backoffice.client.application.update.UpdateClientNameCommand;
import com.auutomate.src.backoffice.client.application.update.UpdateClientNameCommandHandler;
import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientDetailsObjectMother;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.backoffice.client.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.src.frontoffice.client.domain.ClientIdMother;
import com.auutomate.src.shared.aplication.find.ClientNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public class ClientDetailsNameUpdaterTest {
	private Client client;
	private ClientRepository repo;
	private EventBus bus;
	private ClientNameUpdater updater;
	private UpdateClientNameCommandHandler handler;
	private final String newName = "NEW_NAME";
	
	@BeforeEach
	void setup() {
		client = ClientDetailsObjectMother.random();
		repo = Mockito.mock(ClientRepository.class);
		bus = Mockito.mock(EventBus.class);
		updater = new ClientNameUpdater(repo, bus);
		handler = new UpdateClientNameCommandHandler(updater);
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
		ArgumentCaptor<Client> details = ArgumentCaptor.forClass(Client.class);
		verify(repo, times(n)).save(details.capture());
	}

	private void verifyNumberOfBusPubishCalls(int n) throws Exception {
		ArgumentCaptor<ClientNameUpdatedDomainEvent> event = ArgumentCaptor.forClass(ClientNameUpdatedDomainEvent.class);
		verify(bus, times(n)).publish(event.capture());
	}
	
	private void assertClientEquality() {
		ArgumentCaptor<Client> details = ArgumentCaptor.forClass(Client.class);
		verify(repo).save(details.capture());
		assertEquals(client, details.getValue());
	}
	
	private void updateClientName(String newName) throws Exception {
		handler.handle(new UpdateClientNameCommand(client.idValue(), newName));
	}
	
	private void assertEventEquality() throws Exception {
		ArgumentCaptor<ClientNameUpdatedDomainEvent> event = ArgumentCaptor.forClass(ClientNameUpdatedDomainEvent.class);
		verify(bus).publish(event.capture());
		ClientNameUpdatedDomainEvent expectedEvent = new ClientNameUpdatedDomainEvent(client.idValue(), newName);
		assertEquals(expectedEvent.getClientId(), event.getValue().getClientId());
		assertEquals(expectedEvent.getClientName(), event.getValue().getClientName());
	}
	
}
