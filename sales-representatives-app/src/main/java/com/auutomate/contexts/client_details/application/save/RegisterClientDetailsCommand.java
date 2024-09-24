package com.auutomate.contexts.client_details.application.save;

import com.auutomate.contexts.shared.domain.Command;

public final class RegisterClientDetailsCommand implements Command{
	private final String id; 
	private final String name; 
	private final String mail;
	
	public RegisterClientDetailsCommand(String id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}
	
	public String id() {
		return id;
	}
	
	public String name() {
		return name;
	}
	
	public String mail() {
		return mail;
	}

}
