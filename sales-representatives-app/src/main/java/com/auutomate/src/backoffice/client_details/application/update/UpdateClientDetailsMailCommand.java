package com.auutomate.src.backoffice.client_details.application.update;

import com.auutomate.src.shared.domain.Command;

public final class UpdateClientDetailsMailCommand implements Command{
	private final String id;
	private final String mail;
	
	public UpdateClientDetailsMailCommand(String id, String mail) {
		this.id = id;
		this.mail = mail;
	}
	
	public String id() {
		return id;
	}
	
	public String mail() {
		return mail;
	}
}
