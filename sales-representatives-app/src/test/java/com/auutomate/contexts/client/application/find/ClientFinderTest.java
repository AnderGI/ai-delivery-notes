	package com.auutomate.contexts.client.application.find;
	
	import static org.junit.jupiter.api.Assertions.assertEquals;
	import static org.junit.jupiter.api.Assertions.assertNotNull;
	import static org.junit.jupiter.api.Assertions.assertThrows;
	import static org.mockito.Mockito.times;
	import static org.mockito.Mockito.verify;
	import static org.mockito.Mockito.when;
	
	import java.util.List;
	import java.util.Optional;
	
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.mockito.Mockito;
	
	import com.auutomate.contexts.client.domain.Client;
	import com.auutomate.contexts.client.domain.ClientIdMother;
	import com.auutomate.contexts.client.domain.ClientMother;
	import com.auutomate.contexts.client.domain.ClientRepository;
	import com.auutomate.contexts.client.domain.Clients;
	import com.auutomate.contexts.client.domain.ClientsMother;
	import com.auutomate.contexts.client_details.domain.ClientId;
	import com.auutomate.contexts.shared.aplication.find.ClientNotFoundException;
	
	public class ClientFinderTest {
		private ClientRepository repo;
	    private ClientFinder finder;
	    
	    @BeforeEach
	    void setup() {
	        this.repo = this.givenAMockClientRepository();
	        this.finder = this.givenAClientFinder(repo);
	    }
	    
	    @Test
	    void it_should_return_empty_list_with_empty_repository() {
	        
	    	Clients clients = this.givenARandomEmptyClients();
	    	
	        this.whenRepoSearchAllIsCalledVerify(clients);
	        
	        Clients result = finder.findAll();
	        
	        this.verifyThatFinderResultIsNotNull(result);
	        
	        this.verifyClientsSize(clients, result);
	        
	        this.verifyClientRepoSearchAllIsBeingCalledOnce();
	    }
	
	    @Test
	    void it_should_return_a_list_of_clients() {
	        Clients clients = this.givenARandomClients();
	
	        this.whenRepoSearchAllIsCalledVerify(clients);
	
	        Clients result = finder.findAll();
	        
	        this.verifyThatFinderResultIsNotNull(result);
	        
	        this.verifyClientsSize(clients, result);
	        
	        this.checkClientsEquality(clients, result);
	
	        this.verifyClientRepoSearchAllIsBeingCalledOnce();
	    }
	    
	    @Test
	    void it_should_return_an_existing_client() throws ClientNotFoundException {
	    	Client client = this.givenARandomClient();
	    	ClientId id = ClientIdMother.create(client.getId());
	    	
	    	this.verifyThatWhenSearchIsBeingCalledItReturnClientWithId(client, id);
	    	
	    	Client result = finder.find(id);
	    	
	    	this.checkClientEquality(client, result);
	    	this.verifyClientRepoSearchIsBeingCalledOnce(id);
	    }
	    
	    @Test
	    void it_should_throw_an_exception_with_non_existing_client() throws ClientNotFoundException {
	    	ClientId id = ClientIdMother.random();
	    	
	    	this.verifyThatWhenSearchIsBeingCalledItReturnClientWithId(null, id);
	    	
	    	assertThrows(ClientNotFoundException.class, () -> finder.find(id));
	    	
	    	this.verifyClientRepoSearchIsBeingCalledOnce(id);
	    }
	    
	    
	    
	    private void whenRepoSearchAllIsCalledVerify(Clients c) {
	    	when(repo.searchAll()).thenReturn(Optional.of(c));
	    }
	    
	    private ClientRepository givenAMockClientRepository() {
	        return Mockito.mock(ClientRepository.class);
	    }
	    
	    private ClientFinder givenAClientFinder(ClientRepository repo) {
	        return new ClientFinder(repo);
	    }
	
	    private Clients givenARandomClients(){
	    	return ClientsMother.randomThree();
	    }
	    
	    private Clients givenARandomEmptyClients(){
	    	return ClientsMother.empty();
	    }
	    
	    private void verifyClientRepoSearchAllIsBeingCalledOnce() {
	    	verify(this.repo, times(1)).searchAll();
	    }
	    
	    private void verifyClientRepoSearchIsBeingCalledOnce(ClientId id) {
	    	verify(this.repo, times(1)).search(id);
	    }
	    
	    
	    private void verifyThatFinderResultIsNotNull(Clients result) {
	    	assertNotNull(result);
	    }
	    
	    private void verifyClientsSize(Clients expected, Clients actual) {
	    	assertEquals(expected.getAll().size(), actual.getAll().size());
	    }
	    
	    private void checkClientsEquality(Clients clients, Clients result) {
	    	 assertEquals(clients, result);
	    }
	
	    private void checkClientEquality(Client client, Client result) {
	   	 assertEquals(client, result);
	   }
	    
	    private Client givenARandomClient() {
	    	return ClientMother.random();
	    }
	    
	    private void verifyThatWhenSearchIsBeingCalledItReturnClientWithId(Client client, ClientId id) {
	    	when(repo.search(id)).thenReturn(Optional.ofNullable(client));
	    }
	    
	  
	}
