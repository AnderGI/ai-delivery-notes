package com.auutomate.src.shared.domain;

public interface SyncQueryBus {
	<T extends Query> void ask(T query);
}
