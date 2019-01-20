package com.smxknife.servlet.tomcat.demo02.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author smxknife
 * 2019-01-19
 */
public class Demo02AbsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(this.getClass().getName());
		PrintWriter writer = resp.getWriter();
		writer.println(this.getClass().getName());
	}
}
