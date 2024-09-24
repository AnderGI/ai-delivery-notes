package com.auutomate.src.backoffice.client.application.save;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.src.backoffice.client.application.save.ClientRegistar;
import com.auutomate.src.backoffice.client.application.save.RegisterClientCommand;
import com.auutomate.src.backoffice.client.application.save.RegisterClientCommandHandler;
import com.auutomate.src.backoffice.client.domain.Client;
import com.auutomate.src.backoffice.client.domain.ClientDetailsObjectMother;
import com.auutomate.src.backoffice.client.domain.ClientRepository;
import com.auutomate.src.backoffice.client.domain.registar.ClientRegisteredDomainEvent;
import com.auutomate.src.shared.domain.EventBus;

public class ClientDetailsRegistarTest {
    private ClientRepository repo;
    private EventBus eventBus;
    private ClientRegistar saverUseCase;
    private RegisterClientCommandHandler handler;

    @BeforeEach
    void setup() {
        repo = this.givenAClientDetailsRepoMock();
        eventBus = this.givenAEventBusMock();
        saverUseCase = this.givenClientDetailsSaverUseCase(repo, eventBus);
        handler = new RegisterClientCommandHandler(saverUseCase);
    }

    @Test
    void it_should_save_a_valid_client() throws Exception {
        Client client = ClientDetailsObjectMother.random();
        // Call unit test entry point
        this.registerClient(client);
        // Repo save method called once 
        // Check if data used to invoke method matches expected client
        this.verifyRepoSavedClientDetailsOnce(client);
        // Bus called once
        // Check if event data matches client
        this.verifyEventBusPublishedCorrectEventOnlyOnce(client);
    }
    
    // Future test once VO validations added
    
    private void verifyRepoSavedClientDetailsOnce(Client expectedClient) {
        ArgumentCaptor<Client> repoCaptor = ArgumentCaptor.forClass(Client.class);
        Mockito.verify(repo, times(1)).save(repoCaptor.capture());
        assertEquals(expectedClient, repoCaptor.getValue());
    }

   
    private void verifyEventBusPublishedCorrectEventOnlyOnce(Client client) throws Exception {
        ArgumentCaptor<ClientRegisteredDomainEvent> eventCaptor = ArgumentCaptor.forClass(ClientRegisteredDomainEvent.class);
        Mockito.verify(eventBus, times(1)).publish(eventCaptor.capture());

        ClientRegisteredDomainEvent capturedEvent = eventCaptor.getValue();
        assertEquals(client.idValue(), capturedEvent.getClientId());
        assertEquals(client.mailValue(), capturedEvent.getClientMail());
        assertEquals(client.nameValue(), capturedEvent.getClientName());
    }


    private void registerClient(Client client) throws Exception {
        handler.handle(new RegisterClientCommand(client.idValue(), client.nameValue(), client.mailValue()));
    }

    private ClientRepository givenAClientDetailsRepoMock() {
        return Mockito.mock(ClientRepository.class);
    }

    private EventBus givenAEventBusMock() {
        return Mockito.mock(EventBus.class);
    }

    private ClientRegistar givenClientDetailsSaverUseCase(ClientRepository repo, EventBus eventBus) {
        return new ClientRegistar(repo, eventBus);
    }
}
