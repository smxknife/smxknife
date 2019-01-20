package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019-01-16
 */
@WebServlet(name = "paramservlet", loadOnStartup = 0, urlPatterns = "/param", initParams = {
		@WebInitParam(name = "msg", value = " param servlet")
})
public class RequestParameterServlet extends AbsMyServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---------------------------------");
		String parameter = req.getParameter("test");
		System.out.println("getParameter: " + parameter);
		Enumeration<String> parameterNames = req.getParameterNames();
		System.out.println("getParameterNames: ");
		while (parameterNames.hasMoreElements()) {
			System.out.println("     " + parameterNames.nextElement());
		}
		String[] parameterValues = req.getParameterValues("test");
		System.out.println("getParameterValues: ");
		for (String parameterValue : parameterValues) {
			System.out.println("     " + parameterValue);
		}
		Map<String, String[]> parameterMap = req.getParameterMap();
		System.out.print("parameterMap: ");
		parameterMap.entrySet().forEach(entry -> {
			System.out.println("     " + entry.getKey() + ":" + Stream.of(entry.getValue()).collect(Collectors.joining(", ")));
		});
		System.out.println("---------------------------------");
	}
}
