package com.smxknife.java2.thread.futureAndCallable.runnableGet;

import lombok.AllArgsConstructor;

/**
 * @author smxknife
 * 2019-08-19
 */
@AllArgsConstructor
public class RunnableGet implements Runnable {

	private UserInfo info;

	@Override
	public void run() {
		info.setPassword("newPassword");
		info.setUserName("newUserName");
	}
}
