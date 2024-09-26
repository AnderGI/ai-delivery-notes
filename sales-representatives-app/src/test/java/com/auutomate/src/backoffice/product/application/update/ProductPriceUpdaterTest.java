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
import com.auutomate.src.backoffice.product.domain.update.ProductPriceUpdatedDomainEvent;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public class ProductPriceUpdaterTest {

    private ProductRepository repo;
    private EventBus bus;
    private ProductPriceUpdater updater;
    private UpdateProductPriceCommandHandler handler;
    private Product product;
    private String NEW_PRICE;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(ProductRepository.class);
        bus = Mockito.mock(EventBus.class);
        updater = new ProductPriceUpdater(repo, bus);
        handler = new UpdateProductPriceCommandHandler(updater);
        product = ProductMother.random();
        NEW_PRICE = "LOLLL"; // No validations for now
    }

    @Test
    void it_should_update_existing_product_price() throws ProductNotFoundException {
        when(repo.search(ProductReferenceMother.create(product.reference()))).thenReturn(Optional.of(product));
        UpdateProductPriceCommand command = new UpdateProductPriceCommand(product.reference(), NEW_PRICE);
        handler.handle(command);
        verifyRepoSearchCall();
        verifyRepoSaveCall();
        verifyBusPublishCall();
    }

    @Test
    void it_should_throw_exception_when_product_does_not_exist() {
        when(repo.search(ProductReferenceMother.create(product.reference()))).thenReturn(Optional.empty());
        UpdateProductPriceCommand command = new UpdateProductPriceCommand(product.reference(), NEW_PRICE);
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
        assertEquals(NEW_PRICE, saveCaptor.getValue().price());
    }

    private void verifyBusPublishCall() {
        ArgumentCaptor<ProductPriceUpdatedDomainEvent> eventCaptor = ArgumentCaptor.forClass(ProductPriceUpdatedDomainEvent.class);
        verify(bus, times(1)).publish(eventCaptor.capture());
        assertEquals(ProductPriceUpdatedDomainEvent.fromProduct(product), eventCaptor.getValue());
    }
}
