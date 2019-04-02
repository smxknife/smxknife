package com.smxknife.annotation.processor;

import lombok.Getter;

import javax.lang.model.element.Element;

/**
 * @author smxknife
 * 2019-03-28
 */
@Getter
public class ProcessingException extends Exception {

	Element element;

	public ProcessingException(Element element, String msg, Object ...args) {
		super(String.format(msg, args));
		this.element = element;
	}
}
