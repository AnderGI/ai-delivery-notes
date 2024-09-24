package com.auutomate.src.backoffice.client.application.save;
import com.auutomate.src.shared.domain.CommandHandler;

public final class RegisterClientCommandHandler implements CommandHandler<RegisterClientCommand> {

	private final ClientRegistar registar;
	
	public RegisterClientCommandHandler(ClientRegistar registar) {
		this.registar = registar;
	}
	
	@Override
	public void handle(RegisterClientCommand command) throws Exception {
		registar.registar(command.id(), command.name(), command.mail());
	}

}
