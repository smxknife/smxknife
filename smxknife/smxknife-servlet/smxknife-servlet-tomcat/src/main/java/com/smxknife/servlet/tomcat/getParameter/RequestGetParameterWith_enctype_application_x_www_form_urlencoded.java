package com.smxknife.servlet.tomcat.getParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2019/8/26
 */
@WebServlet("/appxwwwformurlencoded")
public class RequestGetParameterWith_enctype_application_x_www_form_urlencoded extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("content type = " + req.getContentType());
		System.out.println("request parameters =======================");
		req.getParameterMap().entrySet().forEach(entry -> {
			String name = entry.getKey();
			String value = Arrays.asList(entry.getValue()).stream().collect(Collectors.joining(", "));
			System.out.printf("name = %s, value = %s\r\n", name, value);
		});
		System.out.println("==========================================");
	}
}
