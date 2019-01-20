package com.smxknife.servlet.tomcat.demo02.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author smxknife
 * 2019-01-20
 */
public class Demo02ServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		print("destroy", sre);
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		print("init", sre);
	}

	private void print(String op, ServletRequestEvent event) {
		System.out.println("-------------------------------------");
		System.out.println(this.getClass().getName() + " " + op);
		System.out.printf("event: { serverName: %s, contextPath: %s, source: %s }\r\n", event.getServletRequest().getServerName(), event.getServletContext().getContextPath(), event.getSource());
	}
}
