package com.auutomate.contexts.client_details.application.update;

import com.auutomate.contexts.shared.domain.Command;

public final class UpdateClientDetailsNameCommand implements Command {
	private final String id;
	private final String name;
	
	public UpdateClientDetailsNameCommand(String id, String name) {
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
