package com.auutomate.contexts.client.application.registar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.contexts.client.application.save.ClientRegistar;
import com.auutomate.contexts.client.application.save.RegistarClientOnClientDetailsRegistered;
import com.auutomate.contexts.client.domain.Client;
import com.auutomate.contexts.client.domain.ClientDetailsRegisteredDomainEventMother;
import com.auutomate.contexts.client.domain.ClientIdMother;
import com.auutomate.contexts.client.domain.ClientMailMother;
import com.auutomate.contexts.client.domain.ClientNameMother;
import com.auutomate.contexts.client.domain.ClientRepository;
import com.auutomate.contexts.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;

public class RegistarClientOnClientDetailsRegisteredTest {
	
	private ClientDetailsRegisteredDomainEvent event;
	private ClientRepository repo;
	private ClientRegistar registar;
	private RegistarClientOnClientDetailsRegistered susbcriber;
	
	@BeforeEach
	void setup() {
		this.event = this.givenAClientDetailsRegisteredDomainEvent();
		this.repo = this.givenAClientRepositoryMock();
		this.registar = this.givenAClientRegistar(repo);
		this.susbcriber = this.givenARegistarClientOnClientDetailsRegistered(registar);
	}
	
	@Test
	void update_client_projection_when_client_updated() {
		
		susbcriber.on(event);		

		ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
		verify(repo, times(1)).save(clientCaptor.capture());
		
		this.verifyCapturedClientDataMatchesEventData(clientCaptor, event);
	}
	
	private ClientRepository givenAClientRepositoryMock() {
		return Mockito.mock(ClientRepository.class);
	}
	
	private ClientRegistar givenAClientRegistar(ClientRepository repo) {
		return new ClientRegistar(repo);
	}
	
	private ClientDetailsRegisteredDomainEvent givenAClientDetailsRegisteredDomainEvent() {
		return ClientDetailsRegisteredDomainEventMother.random();
	}
	
	private RegistarClientOnClientDetailsRegistered givenARegistarClientOnClientDetailsRegistered(ClientRegistar registar) {
		return new RegistarClientOnClientDetailsRegistered(registar);
	}
	
	private void verifyCapturedClientDataMatchesEventData(ArgumentCaptor<Client> clientCaptor, ClientDetailsRegisteredDomainEvent event) {
		assertEquals(clientCaptor.getValue().getId(), event.getClientId());
		assertEquals(clientCaptor.getValue().getName(), event.getClientName());
		assertEquals(clientCaptor.getValue().getMail(), event.getClientMail());
	}
}
