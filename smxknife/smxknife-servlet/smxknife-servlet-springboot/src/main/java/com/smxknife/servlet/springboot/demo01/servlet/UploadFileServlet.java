package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019-01-16
 */
@WebServlet(name = "upload", loadOnStartup = 0, urlPatterns = "/upload", initParams = {
		@WebInitParam(name = "msg", value = " upload servlet")
})
@MultipartConfig(
		location = "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-servlet/smxknife-servlet-springboot/target/temp"
)
public class UploadFileServlet extends AbsMyServlet {

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

		System.out.println("====== multipart/form-data ================");
		Part part = req.getPart("file");
		Collection<Part> parts = req.getParts();
		System.out.println("part: " + part);
		System.out.println("    part contentType: " + part.getContentType());
		System.out.println("    part name: " + part.getName());
		System.out.println("    part submittedFileName: " + part.getSubmittedFileName());
		System.out.println("    part size: " + part.getSize());
		System.out.println("    part headerNames: " + part.getHeaderNames().stream().collect(Collectors.joining(", ")));
		part.getHeaderNames().forEach(name -> {
			System.out.println("    part header " + name + ": " + part.getHeader(name));
		});

		System.out.println("parts: " + parts);
		parts.forEach(p -> {
			System.out.println("    parts part: " + p.getName());
		});
		// 保存文件方式
		part.write("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-servlet/smxknife-servlet-springboot/target/part/" + part.getSubmittedFileName());
		System.out.println("====== multipart/form-data ================");
	}
}
