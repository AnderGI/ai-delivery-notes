package com.auutomate.contexts.client_details.application.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.contexts.client.domain.ClientId;
import com.auutomate.contexts.client.domain.ClientIdMother;
import com.auutomate.contexts.client_details.application.save.ClientDetailsRegistar;
import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsObjectMother;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.ClientDetailsSavedDomainEventMother;
import com.auutomate.contexts.client_details.domain.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.EventBus;

public class ClientDetailsFinderTest {
	@Test
	void it_should_find_an_existing_user() throws ClientNotFoundException {
		// Given
		ClientDetailsRepository mockRepo = this.givenAClientDetailsRepoMock();
		EventBus eventBus = this.givenAEventBusMock();
		ClientDetailsRegistar saver = this.givenClientDetailsSaverUseCase(mockRepo, eventBus);
		ClientDetailsFinder finder = this.givenAClientFinder(mockRepo);
		ArgumentCaptor<ClientDetails> repoCaptor = ArgumentCaptor.forClass(ClientDetails.class);

		this.verifyCorrectClientRegistar(saver, mockRepo, eventBus, repoCaptor);
		this.verifyCorrectClientFinding(finder, mockRepo, repoCaptor);
	}

	@Test
	void it_should_throw_anexception_when_invalid_user_is_found() {
		ClientDetailsRepository mockRepo = this.givenAClientDetailsRepoMock();
		ClientDetailsFinder finder = this.givenAClientFinder(mockRepo);
		this.verifyIncorrectClientFinding(mockRepo, finder);
	}

	private ClientDetailsRepository givenAClientDetailsRepoMock() {
		// TODO Auto-generated method stub
		return Mockito.mock(ClientDetailsRepository.class);
	}

	private ClientDetailsRegistar givenClientDetailsSaverUseCase(ClientDetailsRepository repo, EventBus eventBus) {
		return new ClientDetailsRegistar(repo, eventBus);
	}

	private ClientDetailsFinder givenAClientFinder(ClientDetailsRepository repo) {
		return new ClientDetailsFinder(repo);
	}

	private EventBus givenAEventBusMock() {
		return Mockito.mock(EventBus.class);
	}

	private void verifyCorrectClientRegistar(ClientDetailsRegistar saver, ClientDetailsRepository mockRepo,
			EventBus eventBus, ArgumentCaptor<ClientDetails> repoCaptor) {
		ClientDetails client = ClientDetailsObjectMother.random();
		saver.registar(client.idValue(), client.nameValue(), client.nidValue(), client.mailValue(),
				client.bankAccountValue(), client.billingAddressNameValue(), client.billingPopulationValue(),
				client.billingPostalCodeValue(), client.billingProvinceValue(), client.deliveryAddressNameValue(),
				client.deliveryPopulationValue(), client.deliveryPostalCodeValue(), client.deliveryProvinceValue());
		Mockito.verify(mockRepo, times(1)).save(repoCaptor.capture());
		assertEquals(repoCaptor.getValue().idValue(), client.idValue());

		Mockito.verify(eventBus, times(1)).publish(ClientDetailsSavedDomainEventMother.create(
				repoCaptor.getValue().idValue(), repoCaptor.getValue().mailValue(), repoCaptor.getValue().nameValue()));

	}

	private void verifyCorrectClientFinding(ClientDetailsFinder finder, ClientDetailsRepository mockRepo,
			ArgumentCaptor<ClientDetails> repoCaptor) throws ClientNotFoundException {
	    ClientDetails savedClient = repoCaptor.getValue();
	    ClientId id = ClientIdMother.create(savedClient.idValue());
	    Mockito.when(mockRepo.search(id)).thenReturn(Optional.of(savedClient));
	    ClientDetails found = finder.find(id.id());
	    Mockito.verify(mockRepo, times(1)).search(id);
		assertEquals(found.idValue(), repoCaptor.getValue().idValue());
	}

	private void verifyIncorrectClientFinding(ClientDetailsRepository mockRepo, ClientDetailsFinder finder) {
		ClientId id = ClientIdMother.random();
		Mockito.when(mockRepo.search(id)).thenReturn(Optional.ofNullable(null));

		assertThrows(ClientNotFoundException.class, () -> finder.find(id.id()));
	}
}
