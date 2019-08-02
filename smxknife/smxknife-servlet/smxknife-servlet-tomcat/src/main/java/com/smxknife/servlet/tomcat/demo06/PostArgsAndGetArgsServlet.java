package com.smxknife.servlet.tomcat.demo06;

import com.google.gson.JsonObject;

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
 * 2019-06-17
 */
@WebServlet("/postAndGetArgs")
public class PostArgsAndGetArgsServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JsonObject json = new JsonObject();
		System.out.println("content type = " + req.getContentType());
		json.addProperty("contentType", req.getContentType());
		System.out.println("request parameters =======================");
		JsonObject requestParameter = new JsonObject();
		req.getParameterMap().entrySet().forEach(entry -> {
			String name = entry.getKey();
			String value = Arrays.asList(entry.getValue()).stream().collect(Collectors.joining(", "));
			System.out.printf("name = %s, value = %s\r\n", name, value);
			requestParameter.addProperty(name, value);
		});
		json.add("requestParameter", requestParameter);
		System.out.println("==========================================");

	}
}
