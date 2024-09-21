package com.auutomate.contexts.client_details.application.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.contexts.client.domain.ClientMailMother;
import com.auutomate.contexts.client.domain.ClientNIDMother;
import com.auutomate.contexts.client.domain.ClientNameMother;
import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsBankAccountMother;
import com.auutomate.contexts.client_details.domain.ClientDetailsObjectMother;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.client_details.domain.find.ClientNotFoundException;
import com.auutomate.contexts.client_details.domain.update.ClientBankAccountUpdatedDomainEvent;
import com.auutomate.contexts.client_details.domain.update.ClientMailUpdatedDomainEvent;
import com.auutomate.contexts.client_details.domain.update.ClientNameUpdatedDomainEvent;
import com.auutomate.contexts.client_details.domain.update.ClientNidUpdatedDomainEvent;
import com.auutomate.contexts.shared.domain.DomainEvent;
import com.auutomate.contexts.shared.domain.EventBus;

public class ClientDetailsUpdaterTest {
	private ClientDetailsRepository repo;
	private EventBus bus;
	private ClientDetailsUpdater updater;
	private ClientDetails client;
	private ArgumentCaptor<ClientDetails> clientCaptor;
	
	@BeforeEach
	void variables_setup() {
		repo = this.givenAClientDetailsRepoMock();
		bus = this.givenAnEventBusMock();
		updater = this.givenAClientDetailsUpdater(repo, bus);
		client = this.givenARandomClient();
		clientCaptor = this.givenAClientDetailsArgumentCaptor();
	}
	
	@Test
	void it_should_throw_an_exception_if_non_exiting_client() throws ClientNotFoundException {
		
		verifyRepoSearchReturnValue(client.idValue(), Optional.ofNullable(null));
		
		assertThrows(ClientNotFoundException.class,
				() -> updateClientFromValues(client.idValue(), client.nameValue(), client.nidValue(), client.mailValue(),
						client.bankAccountValue(), client.billingAddressNameValue(), client.billingPopulationValue(),
						client.billingPostalCodeValue(), client.billingProvinceValue(),
						client.deliveryAddressNameValue(), client.deliveryPopulationValue(),
						client.deliveryPostalCodeValue(), client.deliveryProvinceValue()));
		// Since it does not exist it will not call those
		Mockito.verify(repo, never()).save(clientCaptor.capture());
		Mockito.verify(bus, never()).publish(this.givenAnyDomainEvent());
	}
	
	@Test	
	void it_should_update_name_field() throws ClientNotFoundException {
		String toUpdateName = ClientNameMother.random().name();

		verifyRepoSearchReturnValue(client.idValue(), Optional.of(client));
		
		updateClientFromValues(client.idValue(), toUpdateName, client.nidValue(), client.mailValue(),
				client.bankAccountValue(), client.billingAddressNameValue(), client.billingPopulationValue(),
				client.billingPostalCodeValue(), client.billingProvinceValue(),
				client.deliveryAddressNameValue(), client.deliveryPopulationValue(),
				client.deliveryPostalCodeValue(), client.deliveryProvinceValue());
		
		verifyRepoIsOnlyBeingCalledOnceWithCaptoredClass();
		verifyCorrectUpdatedEventIsBeingPublishedAtATime(ClientNameUpdatedDomainEvent.class);
		assertEquals(client, clientCaptor.getValue());
		assertEquals(client.nameValue(), toUpdateName);
	}

	
	@Test	
	void it_should_update_nid_field() throws ClientNotFoundException {
		String toUpdateNid = ClientNIDMother.random().nid();

		verifyRepoSearchReturnValue(client.idValue(), Optional.of(client));
		
		updateClientFromValues(client.idValue(), client.nameValue(), toUpdateNid, client.mailValue(),
				client.bankAccountValue(), client.billingAddressNameValue(), client.billingPopulationValue(),
				client.billingPostalCodeValue(), client.billingProvinceValue(),
				client.deliveryAddressNameValue(), client.deliveryPopulationValue(),
				client.deliveryPostalCodeValue(), client.deliveryProvinceValue());
		
		verifyRepoIsOnlyBeingCalledOnceWithCaptoredClass();
		verifyCorrectUpdatedEventIsBeingPublishedAtATime(ClientNidUpdatedDomainEvent.class);
		assertEquals(client, clientCaptor.getValue());
		assertEquals(client.nidValue(), toUpdateNid);
	}
	
	@Test	
	void it_should_update_both_mail_and_bank_account_fields() throws ClientNotFoundException {
		String mail = ClientMailMother.random().mail();
		String bankAccount = ClientDetailsBankAccountMother.random().account();

		verifyRepoSearchReturnValue(client.idValue(), Optional.of(client));
		
		updateClientFromValues(client.idValue(), client.nameValue(), client.nidValue(), mail,
				bankAccount, client.billingAddressNameValue(), client.billingPopulationValue(),
				client.billingPostalCodeValue(), client.billingProvinceValue(),
				client.deliveryAddressNameValue(), client.deliveryPopulationValue(),
				client.deliveryPostalCodeValue(), client.deliveryProvinceValue());
		
		verifyRepoIsOnlyBeingCalledOnceWithCaptoredClass();
		verifyCorrectUpdatedEventIsBeingPublishedAtATime(ClientMailUpdatedDomainEvent.class);
		verifyCorrectUpdatedEventIsBeingPublishedAtATime(ClientBankAccountUpdatedDomainEvent.class);
		assertEquals(client, clientCaptor.getValue());
		assertEquals(client.mailValue(), mail);
		assertEquals(client.bankAccountValue(), bankAccount);
	}
	
	private void updateClientFromValues(String idValue, String nameValue, String nidValue, String mailValue, String bankAccountValue,
			String billingAddressNameValue, String billingPopulationValue, Integer billingPostalCodeValue,
			String billingProvinceValue, String deliveryAddressNameValue, String deliveryPopulationValue,
			Integer deliveryPostalCodeValue, String deliveryProvinceValue) throws ClientNotFoundException {
		this.updater.update(idValue, nameValue, nidValue, mailValue, bankAccountValue,
				billingAddressNameValue,billingPopulationValue, billingPostalCodeValue,
				billingProvinceValue, deliveryAddressNameValue, deliveryPopulationValue,
				deliveryPostalCodeValue,deliveryProvinceValue);
	}
	
	private void verifyRepoSearchReturnValue(String id, Optional<ClientDetails> client) {
		Mockito.when(this.repo.search(new ClientId(id))).thenReturn(client);
	}
	
	private void verifyCorrectUpdatedEventIsBeingPublishedAtATime(Class<? extends DomainEvent> event) {
		ArgumentCaptor<DomainEvent> captor = ArgumentCaptor.forClass(event);
		Mockito.verify(this.bus).publish(captor.capture());
		assertEquals(captor.getValue().getClass(), event);
	}
	
	private void verifyRepoIsOnlyBeingCalledOnceWithCaptoredClass() {
		Mockito.verify(this.repo, times(1)).save(this.clientCaptor.capture());
	}
	
	private ClientDetailsRepository givenAClientDetailsRepoMock() {
		return Mockito.mock(ClientDetailsRepository.class);
	}

	private EventBus givenAnEventBusMock() {
		return Mockito.mock(EventBus.class);
	}

	private ClientDetailsUpdater givenAClientUpdater(EventBus bus, ClientDetailsRepository repo) {
		return new ClientDetailsUpdater(repo, bus);
	}

	private ArgumentCaptor<ClientDetails> givenAClientDetailsArgumentCaptor() {
		return ArgumentCaptor.forClass(ClientDetails.class);
	}

	private DomainEvent givenAnyDomainEvent() {
		return Mockito.any(DomainEvent.class);
	}
	
	private ClientDetailsUpdater givenAClientDetailsUpdater(ClientDetailsRepository repo, EventBus bus) {
		return new ClientDetailsUpdater(repo, bus);
	}
	
	private ClientDetails givenARandomClient() {
		return ClientDetailsObjectMother.random();
	}
}
