package com.smxknife.java2.unsafe;

/**
 * @author smxknife
 * 2019-08-21
 */
public class UndefineException extends Exception {

	public UndefineException() {
		super();
	}

	public UndefineException(String message) {
		super(message);
	}

	public UndefineException(String message, Throwable cause) {
		super(message, cause);
	}

	public UndefineException(Throwable cause) {
		super(cause);
	}

	protected UndefineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
