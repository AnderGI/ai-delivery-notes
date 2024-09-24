package com.auutomate.src.backoffice.client.application.save;

import com.auutomate.src.shared.domain.Command;

public final class RegisterClientCommand implements Command{
	private final String id; 
	private final String name; 
	private final String mail;
	
	public RegisterClientCommand(String id, String name, String mail) {
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
