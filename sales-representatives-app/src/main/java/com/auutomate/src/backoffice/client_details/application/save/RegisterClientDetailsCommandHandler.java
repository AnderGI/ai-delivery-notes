package com.auutomate.src.backoffice.client_details.application.save;
import com.auutomate.src.shared.domain.CommandHandler;

public final class RegisterClientDetailsCommandHandler implements CommandHandler<RegisterClientDetailsCommand> {

	private final ClientDetailsRegistar registar;
	
	public RegisterClientDetailsCommandHandler(ClientDetailsRegistar registar) {
		this.registar = registar;
	}
	
	@Override
	public void handle(RegisterClientDetailsCommand command) throws Exception {
		registar.registar(command.id(), command.name(), command.mail());
	}

}
