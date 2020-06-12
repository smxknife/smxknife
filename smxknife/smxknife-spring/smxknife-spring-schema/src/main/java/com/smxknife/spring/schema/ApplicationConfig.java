package com.smxknife.spring.schema;

import lombok.Getter;
import lombok.Setter;

/**
 * @author smxknife
 * 2019/12/14
 */
@Getter
@Setter
public class ApplicationConfig {
	private String name;
	private String version;
	private String description;

	public ApplicationConfig() {
		System.out.println(ApplicationConfig.class.getCanonicalName() + " ========= constructed");
	}
}
