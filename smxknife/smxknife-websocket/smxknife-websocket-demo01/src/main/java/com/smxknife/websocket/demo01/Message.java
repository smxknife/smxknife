package com.smxknife.websocket.demo01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author smxknife
 * 2019-08-16
 */
@ToString
@Getter
@Setter
public class Message {

	private String from;
	private String to;
	private String content;
}
