package com.auutomate.src.backoffice.product.application.update;

import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.CommandHandler;

public class UpdateProductDescriptionCommandHandler implements CommandHandler<UpdateProductDescriptionCommand> {

    private final ProductDescriptionUpdater updater;

    public UpdateProductDescriptionCommandHandler(ProductDescriptionUpdater updater) {
        this.updater = updater;
    }

    @Override
    public void handle(UpdateProductDescriptionCommand command) throws ProductNotFoundException {
        updater.update(command.reference(), command.description());
    }
}
