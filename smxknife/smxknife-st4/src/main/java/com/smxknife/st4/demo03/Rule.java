package com.smxknife.st4.demo03;

import lombok.Data;

@Data
public class Rule {
	private String name;
	private String condition;
	private String action;

	public Rule(String name, String condition, String action) {
		this.name = name;
		this.condition = condition;
		this.action = action;
	}
}