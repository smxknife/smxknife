package com.smxknife.ehcache.cache.spring.model;

import java.io.Serializable;

/**
 * @author smxknife
 * 2020/6/11
 */
public class Index implements Serializable {
	public static final long serialVersionUID = 1;
	private Long id;
	private String name;
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
