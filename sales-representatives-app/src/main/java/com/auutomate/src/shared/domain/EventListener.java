package com.auutomate.src.shared.domain;

import java.util.List;

public interface EventListener<T extends DomainEvent> {
	<E extends Exception> void on(T event) throws E;
	List<Class<? extends DomainEvent>> subscribedTo();
}
