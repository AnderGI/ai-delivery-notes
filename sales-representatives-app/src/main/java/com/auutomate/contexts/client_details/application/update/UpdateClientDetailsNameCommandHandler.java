package com.auutomate.contexts.client_details.application.update;

import com.auutomate.contexts.shared.domain.CommandHandler;

public final class UpdateClientDetailsNameCommandHandler implements CommandHandler<UpdateClientDetailsNameCommand> {

	private final ClientDetailsNameUpdater updater;
	
	public UpdateClientDetailsNameCommandHandler(ClientDetailsNameUpdater updater) {
		this.updater = updater;
	}
	
	@Override
	public void handle(UpdateClientDetailsNameCommand command) throws Exception {
		updater.update(command.id(), command.name());
	}

}
