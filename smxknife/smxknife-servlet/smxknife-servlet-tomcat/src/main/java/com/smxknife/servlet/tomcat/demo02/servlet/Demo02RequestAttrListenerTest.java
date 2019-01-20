package com.smxknife.servlet.tomcat.demo02.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-19
 */
@WebServlet(name = "requestAttrListenerTest", urlPatterns = "/demo02/lis/req/attr")
public class Demo02RequestAttrListenerTest extends Demo02AbsServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("hello1", "world1");
		req.setAttribute("hello2", "world2");
		req.setAttribute("hello1", "WORLD1");
		req.removeAttribute("hello2");
		super.doGet(req, resp);
	}
}
