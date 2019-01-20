package com.smxknife.servlet.tomcat.demo02.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-19
 */
@WebServlet(name = "servletContextAttrListenerTest", urlPatterns = "/demo02/lis/ctx/attr")
public class Demo02ServletContextAttrListenerTest extends Demo02AbsServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext servletContext = req.getServletContext();
		servletContext.setAttribute("hello1", "world1");
		servletContext.setAttribute("hello2", "world2");
		servletContext.setAttribute("hello1", "WORLD1");
		servletContext.removeAttribute("hello2");
		super.doGet(req, resp);
	}
}
