package com.auutomate.src.backoffice.product.application.remove;

import com.auutomate.src.shared.aplication.find.ProductNotFoundException;
import com.auutomate.src.shared.domain.CommandHandler;

public class RemoveProductCommandHandler implements CommandHandler<RemoveProductCommand> {

	private final ProductRemover remover;
	
	public RemoveProductCommandHandler(ProductRemover remover) {
		this.remover = remover;
	}
	
	@Override
	public void handle(RemoveProductCommand command) throws ProductNotFoundException {
		remover.remove(command.reference());
	}

}
