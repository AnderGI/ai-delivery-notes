package com.auutomate.src.backoffice.product.application.update;

import com.auutomate.src.backoffice.product.domain.Product;
import com.auutomate.src.backoffice.product.domain.ProductRepository;
import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.EventBus;

public final class ProductDescriptionUpdater {

    private final ProductRepository repo;
    private final EventBus bus;

    public ProductDescriptionUpdater(ProductRepository repo, EventBus bus) {
        this.repo = repo;
        this.bus = bus;
    }

    public void update(String reference, String newDescription) throws ProductNotFoundException {
    	Product.updateDescription(bus, repo, reference, newDescription);
    }
}
