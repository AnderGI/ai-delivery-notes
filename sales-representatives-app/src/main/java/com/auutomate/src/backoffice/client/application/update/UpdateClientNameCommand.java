package com.auutomate.src.backoffice.client.application.update;

import com.auutomate.src.shared.domain.Command;

public final class UpdateClientNameCommand implements Command {
	private final String id;
	private final String name;
	
	public UpdateClientNameCommand(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String id() {
		return id;
	}
	
	public String name() {
		return name;
	}
}
