package com.smxknife.drools.demo03;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2020/6/19
 */
@ToString
@Getter
@Setter
public class ItemConfig {
	private String entCode;
	private String industryCode;
	private String itemCode;
	private double value;
	private List<String> rules = new ArrayList<>();

	public void addRule(String rule) {
		rules.add(rule);
	}
}
