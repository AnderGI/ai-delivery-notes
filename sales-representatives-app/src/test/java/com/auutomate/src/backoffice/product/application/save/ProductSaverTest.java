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
    void it_should_save_product_and_publish_event() throws Exception {
        this.callUseCase(command);

        this.verifyRepoSaveBeingCalledOnce();
        this.verifyBusPublishBeingCalledOnceAndAssertEvent(product);
    }

    private void callUseCase(SaveProductCommand command) throws Exception {
        handler.handle(command);
    }

    private void verifyRepoSaveBeingCalledOnce() {
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(repo, times(1)).save(productCaptor.capture());
        Product capturedProduct = productCaptor.getValue();
        assertEquals(product, capturedProduct);
    }

    private void verifyBusPublishBeingCalledOnceAndAssertEvent(Product expected) throws Exception {
        ArgumentCaptor<ProductSavedDomainEvent> eventCaptor = ArgumentCaptor.forClass(ProductSavedDomainEvent.class);
        verify(bus, times(1)).publish(eventCaptor.capture());
        ProductSavedDomainEvent capturedEvent = eventCaptor.getValue();

        assertEquals(expected.reference(), capturedEvent.reference());
        assertEquals(expected.price(), capturedEvent.price());
        assertEquals(expected.description(), capturedEvent.description());
    }
}
