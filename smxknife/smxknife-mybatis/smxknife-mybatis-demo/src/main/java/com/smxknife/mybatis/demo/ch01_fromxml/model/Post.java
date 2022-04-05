package com.smxknife.mybatis.demo.ch01_fromxml.model;

import lombok.Data;

/**
 * @author smxknife
 * 2020/11/6
 */
@Data
public class Post {
	private Long id;
	private Author author;
	private String content;
}
