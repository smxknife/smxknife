package com.smxknife.spring.ioc;

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

	public Model() {
		this.name = "hello";
	}
}
