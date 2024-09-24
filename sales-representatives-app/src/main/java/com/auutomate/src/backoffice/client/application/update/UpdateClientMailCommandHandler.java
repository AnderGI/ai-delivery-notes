package com.auutomate.src.backoffice.client.application.update;

import com.auutomate.src.shared.domain.CommandHandler;

public class UpdateClientMailCommandHandler implements CommandHandler<UpdateClientMailCommand> {

	private final ClientMailUpdater updater;
	
	public UpdateClientMailCommandHandler(ClientMailUpdater updater) {
		this.updater = updater;
	}
	
	@Override
	public void handle(UpdateClientMailCommand command) throws Exception {
		updater.update(command.id(), command.mail());
	}

}
