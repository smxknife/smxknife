package com.smxknife.mybatis._comm.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/6/18
 */
@ToString
@Data
public class Blog implements Serializable {
	private Long id;
	private String title;
	private String content;
	private Author author;
	private LocalDateTime dateTime;
}
