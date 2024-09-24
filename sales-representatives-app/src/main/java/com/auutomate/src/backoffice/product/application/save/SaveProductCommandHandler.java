package com.auutomate.src.backoffice.product.application.save;

import com.auutomate.src.shared.domain.CommandHandler;

public class SaveProductCommandHandler implements CommandHandler<SaveProductCommand> {

	private final ProductSaver saver;
	
	public SaveProductCommandHandler(ProductSaver saver) {
		this.saver = saver;
	}
	
	@Override
	public void handle(SaveProductCommand command) throws Exception {
		saver.save(command.reference(), command.price(), command.description());
		
	}

}
