package com.auutomate.src.backoffice.product.application.update;

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
import com.auutomate.src.backoffice.product.domain.update.ProductDescriptionUpdatedDomainEvent;
import com.auutomate.src.backoffice.product.domain.update.ProductPriceUpdatedDomainEvent;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public class ProductDescriptionUpdaterTest {
    private ProductRepository repo;
    private EventBus bus;
    private ProductDescriptionUpdater updater;
    private UpdateProductDescriptionCommandHandler handler;
    private Product product;
    private String NEW_DESCRIPTION;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(ProductRepository.class);
        bus = Mockito.mock(EventBus.class);
        updater = new ProductDescriptionUpdater(repo, bus);
        handler = new UpdateProductDescriptionCommandHandler(updater);
        product = ProductMother.random();
        NEW_DESCRIPTION = "LOLLL"; // No validations for now
    }

    @Test
    void it_should_update_existing_product_price() throws ProductNotFoundException {
        when(repo.search(ProductReferenceMother.create(product.reference()))).thenReturn(Optional.of(product));
        UpdateProductDescriptionCommand command = new UpdateProductDescriptionCommand(product.reference(), NEW_DESCRIPTION);
        handler.handle(command);
        verifyRepoSearchCall();
        verifyRepoSaveCall();
        verifyBusPublishCall();
    }

    @Test
    void it_should_throw_exception_when_product_does_not_exist() {
        when(repo.search(ProductReferenceMother.create(product.reference()))).thenReturn(Optional.empty());
        UpdateProductDescriptionCommand command = new UpdateProductDescriptionCommand(product.reference(), NEW_DESCRIPTION);
        assertThrows(ProductNotFoundException.class, () -> handler.handle(command));
        verify(repo, times(0)).save(Mockito.any());
        verify(bus, times(0)).publish(Mockito.any());
    }

    private void verifyRepoSearchCall() {
        ArgumentCaptor<ProductReference> searchCaptor = ArgumentCaptor.forClass(ProductReference.class);
        verify(repo, times(1)).search(searchCaptor.capture());
        assertEquals(ProductReferenceMother.create(product.reference()), searchCaptor.getValue());
    }

    private void verifyRepoSaveCall() {
        ArgumentCaptor<Product> saveCaptor = ArgumentCaptor.forClass(Product.class);
        verify(repo, times(1)).save(saveCaptor.capture());
        assertEquals(product, saveCaptor.getValue());
        assertEquals(NEW_DESCRIPTION, saveCaptor.getValue().price());
    }

    private void verifyBusPublishCall() {
        ArgumentCaptor<ProductDescriptionUpdatedDomainEvent> eventCaptor = ArgumentCaptor.forClass(ProductDescriptionUpdatedDomainEvent.class);
        verify(bus, times(1)).publish(eventCaptor.capture());
        assertEquals(ProductPriceUpdatedDomainEvent.fromProduct(product), eventCaptor.getValue());
    }
}
