package com.auutomate.src.shared.domain;

import java.util.List;

public interface EventBus<T extends DomainEvent> {
	void publish(T event) throws Exception;
}
