package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author smxknife
 * 2019-01-16
 */
@WebServlet(name = "headerServlet", loadOnStartup = 0, urlPatterns = "/header", initParams = {
		@WebInitParam(name = "msg", value = " header servlet")
})
public class HeaderServlet extends AbsMyServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=====================================");
		String parameter = req.getParameter("test");
		System.out.println("getParameter: " + parameter);
		Enumeration<String> headerNames = req.getHeaderNames();
		System.out.println("getHeaderNames: ");
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			System.out.println("header name: " + name);
			System.out.println("        getHeader value: " + req.getHeader(name));
			Enumeration<String> headers = req.getHeaders(name);
			while (headers.hasMoreElements()) {
				System.out.println("        getHeaders value: " + headers.nextElement());
			}
//			System.out.println("        getDateHeaders value: " + req.getDateHeader(name));
//			System.out.println("        getIntHeaders value: " + req.getIntHeader(name));
			System.out.println("-------------------------");
		}
	}
}
