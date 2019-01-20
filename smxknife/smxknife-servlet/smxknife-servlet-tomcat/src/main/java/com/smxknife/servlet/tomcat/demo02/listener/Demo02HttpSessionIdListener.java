package com.smxknife.servlet.tomcat.demo02.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

/**
 * @author smxknife
 * 2019-01-20
 */
public class Demo02HttpSessionIdListener implements HttpSessionIdListener {

	@Override
	public void sessionIdChanged(HttpSessionEvent httpSessionEvent, String s) {
		System.out.println("|||||||||||||||||||||||||||||||");
		System.out.println("param s: " + s);
		System.out.println(httpSessionEvent.getSession().getId());
	}
}
