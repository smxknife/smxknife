package com.smxknife.servlet.annotation.response.retryafter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2020/1/3
 */
@WebServlet(name = "retryafter", urlPatterns = "/retry/*")
public class RetryAfterServlet extends HttpServlet {

	private static int num = 0;
	private static LocalDateTime mark = LocalDateTime.now().plusMinutes(1);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---- doGet");
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		if (uri.contains("503")) {
			mark = LocalDateTime.now().plusMinutes(1);
			resp.setStatus(503);
		}
		LocalDateTime now = LocalDateTime.now();
		if (now.isBefore(mark)) {
			resp.setIntHeader("Retry-After", (int) Duration.between(now, mark).getSeconds());
		}

		if (uri.contains("301")) {
			resp.setStatus(301);
			resp.setHeader("Location", req.getContextPath() + "/comm");
		}

		resp.getWriter().write("num = " + num++);
	}
}
