package com.auutomate.contexts.shared.domain;

import java.util.List;

public interface EventListener<T extends DomainEvent> {
	void on(T event) throws Exception;
	List<Class<? extends DomainEvent>> subscribedTo();
}
