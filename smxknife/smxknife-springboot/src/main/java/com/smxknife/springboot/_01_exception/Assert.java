package com.smxknife.springboot._01_exception;

import java.util.Objects;

/**
 * @author smxknife
 * 2020/6/6
 */
public interface Assert {

	AssertException newException(Object... args);

	AssertException newException(Throwable th, Object... args);

	default void assertNotNull(Object object) {
		if (Objects.isNull(object)) {
			throw newException(object);
		}
	}

	default void assertNotNull(Object object, Object... args) {
		if (Objects.isNull(object)) {
			throw newException(args);
		}
	}
}
