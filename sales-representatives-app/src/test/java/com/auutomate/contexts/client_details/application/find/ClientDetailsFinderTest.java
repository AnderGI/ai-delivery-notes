package com.auutomate.contexts.client_details.application.find;

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
import com.auutomate.contexts.client_details.application.save.ClientDetailsRegistar;
import com.auutomate.contexts.client_details.domain.ClientDetails;
import com.auutomate.contexts.client_details.domain.ClientDetailsObjectMother;
import com.auutomate.contexts.client_details.domain.ClientDetailsRepository;
import com.auutomate.contexts.client_details.domain.ClientDetailsSavedDomainEventMother;
import com.auutomate.contexts.client_details.domain.ClientId;
import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
import com.auutomate.contexts.shared.domain.EventBus;

public class ClientDetailsFinderTest {
	private ClientDetailsRepository repo;
	private ClientDetailsFinder finder;
	
	@BeforeEach
	void setup() {
		repo = this.givenAClientDetailsRepoMock();
		finder = this.givenAClientFinder(repo);
	}
	
	@Test
	void it_should_find_an_existing_client() throws Exception {
		ClientDetails details = ClientDetailsObjectMother.random();
		ClientId id = ClientIdMother.create(details.idValue());
		this.mockClientDetailsSearchResult(id, Optional.ofNullable(details));
		ClientDetails result = finder.find(id.id());
		// Repo search same id
		this.assertSearchCalledWithClientId(id);
		// Rep same client result
		this.assertClientDetailsEquals(result, details);
		// Repo is being called
		this.verifySearchIsBeingCalledOnce(id);
		
	}

	@Test
	void it_should_throw_an_exception_whit_non_existing_client(){
		ClientId id = ClientIdMother.random();
		// Mock repo search save method result
		this.mockClientDetailsSearchResult(id, Optional.ofNullable(null));
		// Not id -> exception
		this.assertClientNotFound(id);
		// Repo search called with same clientid
		this.assertSearchCalledWithClientId(id);
		// Repo search once called
		this.verifySearchIsBeingCalledOnce(id);
	}
	
	private void assertSearchCalledWithClientId(ClientId id) {
		ArgumentCaptor<ClientId> clientId = ArgumentCaptor.forClass(ClientId.class);
		verify(repo).search(clientId.capture());
		assertEquals(id, clientId.getValue());		
	}
	
	private void verifySearchIsBeingCalledOnce(ClientId id) {
		Mockito.verify(repo, times(1)).search(id);
	}

	private void assertClientNotFound(ClientId id) {
		assertThrows(ClientNotFoundException.class, () -> finder.find(id.id()));
	}
	
	private void assertClientDetailsEquals(ClientDetails actual, ClientDetails expected) {
		assertEquals(actual, expected);
	}
	
	private void mockClientDetailsSearchResult(ClientId id, Optional<ClientDetails> expectedResult) {
		Mockito.when(repo.search(id)).thenReturn(expectedResult);
	}

	private ClientDetailsRepository givenAClientDetailsRepoMock() {
		// TODO Auto-generated method stub
		return Mockito.mock(ClientDetailsRepository.class);
	}

	private ClientDetailsFinder givenAClientFinder(ClientDetailsRepository repo) {
		return new ClientDetailsFinder(repo);
	}
}
