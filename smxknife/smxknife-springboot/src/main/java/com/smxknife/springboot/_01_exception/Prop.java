package com.smxknife.springboot._01_exception;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2020/7/31
 */
@Component
@ConfigurationProperties(prefix = "unit")
public class Prop {

	private Map<String, String> energy = new HashMap<>();

	public Map<String, String> getEnergy() {
		return energy;
	}

	public void setEnergy(Map<String, String> energy) {
		this.energy = energy;
	}
}
