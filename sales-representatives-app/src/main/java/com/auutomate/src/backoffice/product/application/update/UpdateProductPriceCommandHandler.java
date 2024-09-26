package com.auutomate.src.backoffice.product.application.update;

import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.CommandHandler;

public final class UpdateProductPriceCommandHandler implements CommandHandler<UpdateProductPriceCommand> {

	private final ProductPriceUpdater updater;
	
	public UpdateProductPriceCommandHandler(ProductPriceUpdater updater) {
		this.updater = updater;
	}
	
	@Override
	public void handle(UpdateProductPriceCommand command) throws ProductNotFoundException {
		updater.update(command.reference(), command.price());
	}

}
