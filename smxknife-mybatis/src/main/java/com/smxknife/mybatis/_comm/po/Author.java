package com.smxknife.mybatis._comm.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author smxknife
 * 2021/6/21
 */
@ToString
@Data
public class Author implements Serializable {
	private Long id;
	private String name;
	private Integer sex;
	private String address;
}
