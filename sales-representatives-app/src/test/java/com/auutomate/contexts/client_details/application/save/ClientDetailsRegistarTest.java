package com.auutomate.contexts.client_details.application.save;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsObjectMother;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.ClientDetailsSavedDomainEventMother;
import com.auutomate.contexts.shared.domain.EventBus;

public class ClientDetailsRegistarTest {

	@Test
	void it_should_save_a_valid_client() {
		ClientDetailsRepository repo = this.givenAClientDetailsRepoMock();
		EventBus eventBus = this.givenAEventBusMock();
		ClientDetailsRegistar saverUseCase = this.givenClientDetailsSaverUseCase(repo, eventBus);
		ClientDetails client = ClientDetailsObjectMother.random();
		// Call use case
		saverUseCase.registar(client.idValue(), client.nameValue(), client.nidValue(), client.mailValue(),
				client.bankAccountValue(), client.billingAddressNameValue(), client.billingPopulationValue(),
				client.billingPostalCodeValue(), client.billingProvinceValue(), client.deliveryAddressNameValue(),
				client.deliveryPopulationValue(), client.deliveryPostalCodeValue(), client.deliveryProvinceValue());
		
		this.verifyRepoAndBusCall(client, repo, eventBus);
	}

	private ClientDetailsRepository givenAClientDetailsRepoMock() {
		// TODO Auto-generated method stub
		return Mockito.mock(ClientDetailsRepository.class);
	}

	private EventBus givenAEventBusMock() {
		return Mockito.mock(EventBus.class);
	}

	private ClientDetailsRegistar givenClientDetailsSaverUseCase(ClientDetailsRepository repo, EventBus eventBus) {
		return new ClientDetailsRegistar(repo, eventBus);
	}
	
	private void verifyRepoAndBusCall(ClientDetails client, ClientDetailsRepository repo, EventBus eventBus) {
		ArgumentCaptor<ClientDetails> repoCaptor = ArgumentCaptor.forClass(ClientDetails.class);
		Mockito.verify(repo, times(1)).save(repoCaptor.capture());
		assertEquals(repoCaptor.getValue(), client); 

		Mockito.verify(eventBus, times(1)).publish(
				ClientDetailsSavedDomainEventMother.create(repoCaptor.getValue().idValue(), repoCaptor.getValue().mailValue(), repoCaptor.getValue().nameValue()));

	}
	
}
