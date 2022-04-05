package com.smxknife.springboot._08_exceptionhandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author smxknife
 * 2021/8/1
 */
@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler
	public String handle(Exception ex) {
		return "Ex: " + ex.getMessage();
	}

	@ExceptionHandler(RuntimeException.class)
	public String handle2(RuntimeException ex) {
		return "Rx: " + ex.getMessage();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String handle3(IllegalArgumentException ex) {
		return "Ill: " + ex.getMessage();
	}
}
