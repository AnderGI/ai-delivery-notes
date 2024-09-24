package com.auutomate.src.shared.domain;

public interface CommandHandler <C extends Command>{
	void handle(C command) throws Exception;
}
