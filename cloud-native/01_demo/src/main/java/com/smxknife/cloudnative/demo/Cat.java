package com.smxknife.cloudnative.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
public class Cat {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	public Cat() {
	}

	public Cat(String name) {
		this.name = name;
	}
}