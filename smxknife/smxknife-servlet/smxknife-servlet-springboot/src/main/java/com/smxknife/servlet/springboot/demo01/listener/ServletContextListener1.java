package com.smxknife.servlet.springboot.demo01.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author smxknife
 * 2018-12-26
 */
@WebListener
public class ServletContextListener1 implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("init servlet context");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("destroy servlet context");
	}
}
