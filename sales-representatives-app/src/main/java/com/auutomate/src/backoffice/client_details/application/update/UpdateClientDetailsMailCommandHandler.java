package com.auutomate.src.backoffice.client_details.application.update;

import com.auutomate.src.shared.domain.CommandHandler;

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
