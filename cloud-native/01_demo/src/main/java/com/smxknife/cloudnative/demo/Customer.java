package com.smxknife.cloudnative.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author smxknife
 * 2020/5/23
 */
@Entity
@Getter
@Setter
public class Customer {
	@Id
	private long id;
	private String email;
	public Customer() {}
	public Customer(long id, String email) {
		this.id = id;
		this.email = email;
	}
}
