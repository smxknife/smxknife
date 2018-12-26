package com.smxknife.servlet.tomcat.demo01.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2018-12-26
 */
public class MyHahaServlet1 extends HahaServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("initializer haha my haha servlet1 request");
	}
}
