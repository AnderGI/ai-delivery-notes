package com.auutomate.src.frontoffice.client.application.registar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.src.backoffice.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;
import com.auutomate.src.frontoffice.client.application.registar.ClientRegistar;
import com.auutomate.src.frontoffice.client.application.registar.RegistarClientOnClientDetailsRegistered;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.frontoffice.client.domain.ClientDetailsRegisteredDomainEventMother;
import com.auutomate.src.frontoffice.client.domain.ClientRepository;

public class RegistarClientOnClientDetailsRegisteredTest {

    private ClientDetailsRegisteredDomainEvent event;
    private ClientRepository repo;
    private ClientRegistar registar;
    private RegistarClientOnClientDetailsRegistered subscriber;

    @BeforeEach
    void setup() {
        event = givenAClientDetailsRegisteredDomainEvent();
        repo = givenAClientRepositoryMock();
        registar = givenAClientRegistar(repo);
        subscriber = givenARegistarClientOnClientDetailsRegistered(registar);
    }

    @Test
    void update_client_projection_when_client_updated() {
        // Act
        subscriber.on(event);

        // Capture and verify
        ArgumentCaptor<Client> clientCaptor = captureClientFromRepoSave();
        verifyClientRepoSaveCalledOnce();

        // Assert
        verifyCapturedClientDataMatchesEventData(clientCaptor, event);
    }

    // Helper Methods

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

    private ArgumentCaptor<Client> captureClientFromRepoSave() {
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(repo).save(clientCaptor.capture());
        return clientCaptor;
    }

    private void verifyClientRepoSaveCalledOnce() {
        verify(repo, times(1)).save(Mockito.any(Client.class));
    }

    private void verifyCapturedClientDataMatchesEventData(ArgumentCaptor<Client> clientCaptor, ClientDetailsRegisteredDomainEvent event) {
        Client capturedClient = clientCaptor.getValue();
        assertEquals(capturedClient.getId(), event.getClientId());
        assertEquals(capturedClient.getName(), event.getClientName());
        assertEquals(capturedClient.getMail(), event.getClientMail());
    }
}
