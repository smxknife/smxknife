package com.smxknife.servlet.tomcat.demo04.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-20
 */
@WebServlet("/demo04/*")
public class Demo04DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println("url: " + uri);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.html");
		dispatcher.include(req, resp);
	}
}
