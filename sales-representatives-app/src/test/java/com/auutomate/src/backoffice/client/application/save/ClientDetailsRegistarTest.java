package com.auutomate.src.backoffice.client.application.save;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.src.backoffice.client.domain.ClientDetailsObjectMother;
import com.auutomate.src.backoffice.client_details.application.save.ClientDetailsRegistar;
import com.auutomate.src.backoffice.client_details.application.save.RegisterClientDetailsCommand;
import com.auutomate.src.backoffice.client_details.application.save.RegisterClientDetailsCommandHandler;
import com.auutomate.src.backoffice.client_details.domain.ClientDetails;
import com.auutomate.src.backoffice.client_details.domain.ClientDetailsRepository;
import com.auutomate.src.backoffice.client_details.domain.registar.ClientDetailsRegisteredDomainEvent;
import com.auutomate.src.shared.domain.EventBus;

public class ClientDetailsRegistarTest {
    private ClientDetailsRepository repo;
    private EventBus eventBus;
    private ClientDetailsRegistar saverUseCase;
    private RegisterClientDetailsCommandHandler handler;

    @BeforeEach
    void setup() {
        repo = this.givenAClientDetailsRepoMock();
        eventBus = this.givenAEventBusMock();
        saverUseCase = this.givenClientDetailsSaverUseCase(repo, eventBus);
        handler = new RegisterClientDetailsCommandHandler(saverUseCase);
    }

    @Test
    void it_should_save_a_valid_client() throws Exception {
        ClientDetails client = ClientDetailsObjectMother.random();
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
    
    private void verifyRepoSavedClientDetailsOnce(ClientDetails expectedClient) {
        ArgumentCaptor<ClientDetails> repoCaptor = ArgumentCaptor.forClass(ClientDetails.class);
        Mockito.verify(repo, times(1)).save(repoCaptor.capture());
        assertEquals(expectedClient, repoCaptor.getValue());
    }

   
    private void verifyEventBusPublishedCorrectEventOnlyOnce(ClientDetails client) throws Exception {
        ArgumentCaptor<ClientDetailsRegisteredDomainEvent> eventCaptor = ArgumentCaptor.forClass(ClientDetailsRegisteredDomainEvent.class);
        Mockito.verify(eventBus, times(1)).publish(eventCaptor.capture());

        ClientDetailsRegisteredDomainEvent capturedEvent = eventCaptor.getValue();
        assertEquals(client.idValue(), capturedEvent.getClientId());
        assertEquals(client.mailValue(), capturedEvent.getClientMail());
        assertEquals(client.nameValue(), capturedEvent.getClientName());
    }


    private void registerClient(ClientDetails client) throws Exception {
        handler.handle(new RegisterClientDetailsCommand(client.idValue(), client.nameValue(), client.mailValue()));
    }

    private ClientDetailsRepository givenAClientDetailsRepoMock() {
        return Mockito.mock(ClientDetailsRepository.class);
    }

    private EventBus givenAEventBusMock() {
        return Mockito.mock(EventBus.class);
    }

    private ClientDetailsRegistar givenClientDetailsSaverUseCase(ClientDetailsRepository repo, EventBus eventBus) {
        return new ClientDetailsRegistar(repo, eventBus);
    }
}
