package com.smxknife.servlet.tomcat.demo03.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2019-01-20
 */
@WebServlet(name = "helloHtmlServlet", urlPatterns = "/demo03/hello/html")
public class HelloHtmlServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream is = req.getServletContext().getResourceAsStream("/hello.html");
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader bufferedReader = new BufferedReader(reader);
		PrintWriter writer = resp.getWriter();
		writer.println(bufferedReader.lines().collect(Collectors.joining()));
	}
}
