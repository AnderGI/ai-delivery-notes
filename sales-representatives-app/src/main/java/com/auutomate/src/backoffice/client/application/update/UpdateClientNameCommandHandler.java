package com.auutomate.src.backoffice.client.application.update;

import com.auutomate.src.shared.domain.CommandHandler;

public final class UpdateClientNameCommandHandler implements CommandHandler<UpdateClientNameCommand> {

	private final ClientNameUpdater updater;
	
	public UpdateClientNameCommandHandler(ClientNameUpdater updater) {
		this.updater = updater;
	}
	
	@Override
	public void handle(UpdateClientNameCommand command) throws Exception {
		updater.update(command.id(), command.name());
	}

}
