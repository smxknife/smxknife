package com.smxknife.servlet.annotation.response.connectionAndkeepalive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2020/1/10
 */
@WebServlet(name = "connection", urlPatterns = "/connection/*")
public class ConnectionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String requestURI = req.getRequestURI();
		resp.getWriter().write("Connection");
		if (requestURI.contains("keep")) {
			resp.setHeader("Connection", "keep-alive");
		}
		if (requestURI.contains("close")) {
			resp.setHeader("Connection", "close");
		}
		if (requestURI.contains("keepalive/10")) {
			resp.setIntHeader("Keep-Alive", 10);
		}
		if (requestURI.contains("keepalibe/max")) {
			resp.setHeader("keep-Alive", "max=5");
		}

		if (requestURI.contains("keepalive/timeout/max")) {
			resp.setHeader("keep-alive", "timeout=60, max=10");
		}
	}
}
