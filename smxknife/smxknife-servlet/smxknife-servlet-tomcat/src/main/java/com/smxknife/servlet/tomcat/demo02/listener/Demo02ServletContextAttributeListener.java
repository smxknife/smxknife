package com.smxknife.servlet.tomcat.demo02.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * @author smxknife
 * 2019-01-20
 */
public class Demo02ServletContextAttributeListener implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		print("add attr", event);
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		print("remove attr", event);
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		print("replace attr", event);
	}

	private void print(String op, ServletContextAttributeEvent event) {
		System.out.println("=========================================");
		System.out.println(this.getClass().getName() + " " + op);
		System.out.printf("event: { name: %s, value: %s, source: %s }\r\n", event.getName(), event.getValue(), event.getSource());
	}
}
