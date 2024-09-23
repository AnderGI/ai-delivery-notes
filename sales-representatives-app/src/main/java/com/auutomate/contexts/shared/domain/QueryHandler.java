package com.auutomate.contexts.shared.domain;

public interface QueryHandler <Q extends Query, R extends Response> {
	R handle(Q query) throws Exception;
}
