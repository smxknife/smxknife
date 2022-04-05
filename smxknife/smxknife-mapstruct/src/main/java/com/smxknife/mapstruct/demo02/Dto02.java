package com.smxknife.mapstruct.demo02;

import lombok.Data;

/**
 * @author smxknife
 * 2021/3/8
 */
public class Dto02 {
	private int id;
	private String name;
	private Double value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
