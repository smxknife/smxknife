package com.smxknife.servlet.dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/28
 */
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doDispatcher(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String requestURI = req.getRequestURI();
		System.out.println("requestURI: " + requestURI);
		StringBuffer requestURL = req.getRequestURL();
		System.out.println("requestURL: " + requestURL.toString());
		String contextPath = req.getContextPath();
		System.out.println("contextPath: " + contextPath);
		String pathInfo = req.getPathInfo();
		System.out.println("pathInfo: " + pathInfo);
		String queryString = req.getQueryString();
		System.out.println("queryString: " + queryString);
		String servletPath = req.getServletPath();
		System.out.println("servletPath: " + servletPath);

		String mid = req.getParameter("mid");
		if ("/getMemberById".equals(pathInfo)) {
			new MemberController().getMemberById(mid);
		} else if ("/getOrderById".equals(pathInfo)) {
			new OrderController().getOrderById(mid);
		} else if ("/logout".equals(pathInfo)) {
			new SystemController().logout();
		} else {
			resp.getWriter().write("404 Not Found!");
		}

	}
}
