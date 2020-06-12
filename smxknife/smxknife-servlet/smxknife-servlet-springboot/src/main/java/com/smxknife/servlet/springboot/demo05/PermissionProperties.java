package com.smxknife.servlet.springboot.demo05;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2020/2/14
 */
@Component
@ConfigurationProperties(prefix = "permission")
public class PermissionProperties {

	private String model = "NONE";
	private String kernelUrl = "";
	private String key = "qweBGds$2OL";

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getKernelUrl() {
		return kernelUrl;
	}

	public void setKernelUrl(String kernelUrl) {
		this.kernelUrl = kernelUrl;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
