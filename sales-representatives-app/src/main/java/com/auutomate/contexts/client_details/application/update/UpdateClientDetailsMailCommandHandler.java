package com.auutomate.contexts.client_details.application.update;

import com.auutomate.contexts.shared.domain.CommandHandler;

public class UpdateClientDetailsMailCommandHandler implements CommandHandler<UpdateClientDetailsMailCommand> {

	private final ClientDetailsMailUpdater updater;
	
	public UpdateClientDetailsMailCommandHandler(ClientDetailsMailUpdater updater) {
		this.updater = updater;
	}
	
	@Override
	public void handle(UpdateClientDetailsMailCommand command) throws Exception {
		updater.update(command.id(), command.mail());
	}

}
