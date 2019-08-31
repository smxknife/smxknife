package com.smxknife.java2.thread.completionservice.demo03;

import lombok.AllArgsConstructor;

/**
 * @author smxknife
 * 2019/8/27
 */
@AllArgsConstructor
public class MyRunnable implements Runnable {

	private UserInfo userInfo;

	@Override
	public void run() {
		userInfo.setUsername("userName-Value");
		userInfo.setPassword("password-pwd");
		System.out.println("run over!!!!");

	}
}
