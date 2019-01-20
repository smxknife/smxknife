package com.smxknife.servlet.tomcat.demo02.listener;

import javax.servlet.*;

/**
 * @author smxknife
 * 2019-01-20
 */
public class Demo02RequestAttributeListener implements ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		print("add attr", srae);
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		print("remove attr", srae);
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		print("replace attr", srae);
	}

	private void print(String op, ServletRequestAttributeEvent event) {
		System.out.println("**********************************************");
		System.out.println(this.getClass().getName() + " " + op);
		System.out.printf("event: { name: %s, value: %s, source: %s }\r\n", event.getName(), event.getValue(), event.getSource());
	}
}
