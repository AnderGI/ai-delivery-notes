package com.auutomate.src.backoffice.product.application.save;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductMother;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.backoffice.product.domain.save.ProductSavedDomainEvent;
import com.auutomate.src.shared.domain.Command;
import com.auutomate.src.shared.domain.EventBus;

public class ProductSaverTest {
	private ProductRepository repo;
	private EventBus bus;
	private Product product;
	private ProductSavedDomainEvent event;
	private ProductSaver saver;
	private SaveProductCommand command;
	private SaveProductCommandHandler handler;
	
	@BeforeEach
	void setup() {
		repo = Mockito.mock(ProductRepository.class);
		bus = Mockito.mock(EventBus.class);
		product = ProductMother.random();
		event = ProductSavedDomainEvent.fromProduct(product);
		saver = new ProductSaver(repo, bus);
		command = new SaveProductCommand(product.reference(), product.price(), product.description());
		handler = new SaveProductCommandHandler(saver);
	}
	
	@Test
	void it_should_save_a_product() throws Exception {
		this.callUseCase(command);
	
		this.verifyRepoSaveBeingCalledOnce();
		this.assertProductEquality(product);
		
		this.verifyBusPublishBeingCalledOnce();
		this.assertProductAndEventEquality(product);
	}
	
	private void callUseCase(SaveProductCommand command) throws Exception {
		handler.handle(command);
	}
	
	private void verifyRepoSaveBeingCalledOnce() {
		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(repo, times(1)).save(productCaptor.capture());
	}
	
	private void assertProductEquality(Product expected) {
		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(repo).save(productCaptor.capture());
		assertEquals(product, productCaptor.getValue());
	}

	private void verifyBusPublishBeingCalledOnce() throws Exception {
		ArgumentCaptor<ProductSavedDomainEvent> eventCaptor = ArgumentCaptor.forClass(ProductSavedDomainEvent.class);
		verify(bus, times(1)).publish(eventCaptor.capture());
	}
	
	private void assertProductAndEventEquality(Product expected) throws Exception {
		ArgumentCaptor<ProductSavedDomainEvent> eventCaptor = ArgumentCaptor.forClass(ProductSavedDomainEvent.class);
		verify(bus).publish(eventCaptor.capture());
		assertEquals(product.reference(), eventCaptor.getValue().reference());
		assertEquals(product.price(), eventCaptor.getValue().price());
		assertEquals(product.description(), eventCaptor.getValue().description());
	}	
	
	
}
