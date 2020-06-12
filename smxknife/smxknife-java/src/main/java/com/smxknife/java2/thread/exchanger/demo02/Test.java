package com.smxknife.java2.thread.exchanger.demo02;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author smxknife
 * 2020/6/5
 */
public class Test {
	public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(1589551491000L), ZoneId.systemDefault());
		System.out.println(localDateTime);
	}
}
