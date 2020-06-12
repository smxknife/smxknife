package com.smxknife.spring.ioc.xml;

import lombok.Getter;
import lombok.Setter;

/**
 * @author smxknife
 * 2019/12/17
 */
@Getter
@Setter
public class Model {
	private String name;

	private PropertyA propertyA;

	public Model() {
		this.name = "hello";
	}
}
