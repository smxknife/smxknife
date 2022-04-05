package com.smxknife.cache.memcached.demo01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author smxknife
 * 2020/6/20
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int age;

}
