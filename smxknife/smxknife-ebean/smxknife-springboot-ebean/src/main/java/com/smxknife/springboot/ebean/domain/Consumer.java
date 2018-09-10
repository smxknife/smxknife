package com.smxknife.springboot.ebean.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author smxknife
 * 2018/8/30
 */
@Entity
public class Consumer {
	@Id
	long id;

	String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
