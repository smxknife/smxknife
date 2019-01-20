package com.smxknife.servlet.tomcat.demo02.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author smxknife
 * 2019-01-20
 */
public class Demo02HttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		print("create", se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		print("destroy", se);
	}

	private void print(String op, HttpSessionEvent event) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(this.getClass().getName() + " " + op);
		System.out.printf("event: { sessionId: %s, isNew: %s, source: %s }\r\n", event.getSession().getId(), event.getSession().isNew(), event.getSource());
	}
}
