package com.auutomate.src.backoffice.product.application.remove;

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

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductMother;
import com.auutomate.src.backoffice.product.domain.ProductReference;
import com.auutomate.src.backoffice.product.domain.ProductReferenceMother;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.backoffice.product.domain.remove.ProductRemovedDomainEvent;
import com.auutomate.src.frontoffice.client.domain.Client;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public class ProductRemoverTest {
	private ProductRepository repo;
	private EventBus bus;
	private Product product;
	private ProductRemovedDomainEvent event;
	private ProductRemover remover;
	private RemoveProductCommand command;
	private RemoveProductCommandHandler handler;
	
	@BeforeEach
	void setup() {
		repo = Mockito.mock(ProductRepository.class);
		bus = Mockito.mock(EventBus.class);
		product = ProductMother.random();
		event = new ProductRemovedDomainEvent(product.reference());
		remover = new ProductRemover(bus, repo);
		command = RemoveProductCommand.fromProduct(product);
		handler = new RemoveProductCommandHandler(remover);
	}
	
	@Test
	void it_should_remove_a_product() throws ProductNotFoundException {
		this.verifyRepoSearchReturn(ProductReferenceMother.create(product.reference()),Optional.ofNullable(product));
		this.callUseCase();
		this.verifyRepoRemoveCall();
		this.verifyBusPublishCall();
	}

	@Test
	void it_should_throw_an_exception_when_non_existing_product() {
		this.verifyRepoSearchReturn(ProductReferenceMother.create(product.reference()),Optional.ofNullable(null));
		assertThrows(ProductNotFoundException.class, () -> this.callUseCase());
	}
	
	private void callUseCase() throws ProductNotFoundException {
		handler.handle(command);
	}
	
	private void verifyRepoSearchReturn(ProductReference reference, Optional<Product> product) {
		when(repo.search(reference)).thenReturn(product);
	}
	
	private void verifyRepoRemoveCall() {
		ArgumentCaptor<ProductReference> referenceCaptor = ArgumentCaptor.forClass(ProductReference.class);
		verify(repo, times(1)).remove(referenceCaptor.capture());
		assertEquals(ProductReferenceMother.create(product.reference()), referenceCaptor.getValue());
	}

	private void verifyBusPublishCall() {
		ArgumentCaptor<ProductRemovedDomainEvent> eventCaptor = ArgumentCaptor.forClass(ProductRemovedDomainEvent.class);
		verify(bus, times(1)).publish(eventCaptor.capture());
		assertEquals(new ProductRemovedDomainEvent(product.reference()), eventCaptor.getValue());		
	}

}
