package com.auutomate.src.shared.domain;

public interface QueryHandler <Q extends Query, R extends Response, E extends Exception> {
	R handle(Q query) throws E;
}
