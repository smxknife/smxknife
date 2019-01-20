package com.smxknife.servlet.tomcat.demo02.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-19
 */
@WebServlet(name = "sessionListenerTest", urlPatterns = "/demo02/lis/session/attr")
public class Demo02HttpSessionAttributeListenerTest extends Demo02AbsServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		session.setAttribute("hello1", "world1");
		session.setAttribute("hello2", "world2");
		session.setAttribute("hello1", "WORLD1");
		session.removeAttribute("hello2");
		session.invalidate();
		super.doGet(req, resp);
	}
}
