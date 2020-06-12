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
@WebServlet(name = "refreshCustom", urlPatterns = "/refresh/custom/*")
public class RefreshCustomServlet extends HttpServlet {

	private static int no = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numParam = req.getParameter("num");
		int num = 3;
		try {
			num = Integer.valueOf(numParam);
		} catch (Exception e) {}
		resp.setIntHeader("Refresh", num);
		resp.getWriter().write(num + " seconds refresh! No = " + no++);
	}
}
