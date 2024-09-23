package com.auutomate.contexts.shared.domain;

public interface SyncQueryBus {
	<T extends Query> void ask(T query);
}
