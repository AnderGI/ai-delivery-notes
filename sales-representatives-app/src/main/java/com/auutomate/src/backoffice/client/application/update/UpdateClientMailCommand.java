package com.auutomate.src.backoffice.client.application.update;

import com.auutomate.src.shared.domain.Command;

public final class UpdateClientMailCommand implements Command{
	private final String id;
	private final String mail;
	
	public UpdateClientMailCommand(String id, String mail) {
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
