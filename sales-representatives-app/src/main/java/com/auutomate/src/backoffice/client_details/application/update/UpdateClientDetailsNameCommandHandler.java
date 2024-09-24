package com.auutomate.src.backoffice.client_details.application.update;

import com.auutomate.src.shared.domain.CommandHandler;

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
