package com.smxknife.servlet.tomcat.demo02.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author smxknife
 * 2019-01-20
 */
public class Demo02HttpSessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		print("add attr", event);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		print("remove attr", event);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		print("replace attr", event);
	}

	private void print(String op, HttpSessionBindingEvent event) {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println(this.getClass().getName() + " " + op);
		System.out.printf("event: { name: %s, session: %s, value: %s, source: %s }\r\n", event.getName(), event.getSession().getId(), event.getValue(), event.getSource());
	}
}
