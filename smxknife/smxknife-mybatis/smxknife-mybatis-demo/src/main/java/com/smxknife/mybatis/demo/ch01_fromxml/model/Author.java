package com.smxknife.mybatis.demo.ch01_fromxml.model;

import lombok.Data;

/**
 * @author smxknife
 * 2020/11/6
 */
@Data
public class Author {
	private Long id;
	private String username;
	private String password;
	private String email;
}
