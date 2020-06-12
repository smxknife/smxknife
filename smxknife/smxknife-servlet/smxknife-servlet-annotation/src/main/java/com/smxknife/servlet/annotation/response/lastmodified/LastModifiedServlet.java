package com.smxknife.servlet.annotation.response.lastmodified;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2020/1/6
 */
@WebServlet(name = "lastModified", urlPatterns = "/last/*")
public class LastModifiedServlet extends HttpServlet {

	private static int num = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI();
		if (uri.contains("mod")) {
			resp.setDateHeader("Last-Modified", System.currentTimeMillis());
		}
		resp.setContentType("text/text");

		resp.getWriter().write(num++);
	}
}
