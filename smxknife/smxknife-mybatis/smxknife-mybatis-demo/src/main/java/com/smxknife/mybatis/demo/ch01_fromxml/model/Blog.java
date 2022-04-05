package com.smxknife.mybatis.demo.ch01_fromxml.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author smxknife
 * 2020/11/6
 */
@ToString
@Getter
@Setter
public class Blog {
	private Long id;
	private String title;
	private Author author;
	private List<Post> posts;

//	public Blog() {}
//
//	public Blog(long id) {
//		this.id = id;
//	}
}
