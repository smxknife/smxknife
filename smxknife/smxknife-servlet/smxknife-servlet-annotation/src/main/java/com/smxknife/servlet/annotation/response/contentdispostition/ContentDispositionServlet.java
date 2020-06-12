package com.smxknife.servlet.annotation.response.contentdispostition;

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
@WebServlet(name = "contentDisposition", urlPatterns = "/contentdisposition/*")
public class ContentDispositionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("T -- T");
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) {
		String requestURI = req.getRequestURI();
		String name = "";
		if (requestURI.contains("app")) {
			resp.setHeader("Content-Type", "application/octet-stream");
			name += "app_";
		}
		if (requestURI.contains("inline")) {
			name += "inline.pdf";
			resp.setHeader("Content-Disposition", "inline;filename=" + name);
		} else {
			name += "attachment.pdf";
			resp.setHeader("Content-Disposition", "attachment;filename=" + name);
		}
	}

	private void doInline(HttpServletRequest req, HttpServletResponse resp) {
		resp.setHeader("Content-Disposition", "inline;filename=test.pdf");
	}
}
