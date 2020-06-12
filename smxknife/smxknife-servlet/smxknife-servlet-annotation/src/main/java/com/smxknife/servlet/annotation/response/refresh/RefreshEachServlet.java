package com.smxknife.servlet.annotation.response.refresh;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/31
 */
@WebServlet(name = "refreshEach", urlPatterns = "/refresh/each/*")
public class RefreshEachServlet extends HttpServlet {

	private static int no = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setIntHeader("Refresh", 5);
		resp.getWriter().write("No = " + no++);
	}
}
