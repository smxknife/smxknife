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
@WebServlet(name = "refreshToOther", urlPatterns = "/refresh/other/*")
public class RefreshToOtherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Refresh", "3;" + req.getContextPath() + "/comm");
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write("三秒后刷新到Comm页面");
	}
}
