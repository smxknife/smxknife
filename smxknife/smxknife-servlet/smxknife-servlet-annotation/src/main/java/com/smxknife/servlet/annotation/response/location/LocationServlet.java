package com.smxknife.servlet.annotation.response.location;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2020/1/2
 */
@WebServlet(name = "location", urlPatterns = "/location/*")
public class LocationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---- doGet");
		dispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {
		System.out.println("---- doPost");
		dispatch(req, resp);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---- doHead");
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("---- dispatch: " + req.getMethod());
		resp.setHeader("Location", req.getContextPath() + "/comm");
		String uri = req.getRequestURI();
		boolean isCacheAble = uri.contains("cache");
		String content = "200";
		if (uri.contains("303")) {
			content = "303";
		} else if (uri.contains("301")) {
			content = "301";
		} else if (uri.contains("302")) {
			content = "302";
		} else if (uri.contains("307")) {
			content = "307";
		} else if (uri.contains("308")) {
			content = "308";
		} else if (uri.contains("201")) {
			content = "201";
		}

		if (isCacheAble) {
			resp.setHeader("Cache-Control", "max-age=600");
		}

		resp.setStatus(Integer.valueOf(content));
		resp.getWriter().write(content);
	}
}
