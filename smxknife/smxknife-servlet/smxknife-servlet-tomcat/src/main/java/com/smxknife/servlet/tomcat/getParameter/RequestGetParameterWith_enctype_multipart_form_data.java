package com.smxknife.servlet.tomcat.getParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author smxknife
 * 2019/8/26
 */
@WebServlet("/multiformdata")
@MultipartConfig(
		location = "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-servlet/smxknife-servlet-tomcat/target/temp"
)
public class RequestGetParameterWith_enctype_multipart_form_data extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("content type = " + req.getContentType());
		System.out.println("request parameters =======================");

		String boundary = req.getHeader("boundary");
		System.out.println("request boundary = " + boundary);

		Part name = req.getPart("name");
		System.out.println("part name : " + name.getName());

		req.getParts().stream().forEach(part -> {
			System.out.println(part.getName());
			part.getHeaderNames().forEach(headername -> {
				System.out.println(part.getName() + " - headerName = " + headername);
			});
			System.out.println(part.getName() + " - submittedFileName = " + part.getSubmittedFileName());
			try {
				char[] chars = new char[(int) part.getSize()];
				new InputStreamReader(part.getInputStream()).read(chars);
				System.out.println(part.getName() + " - content = " + new String(chars));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

//		req.getParameterMap().entrySet().forEach(entry -> {
//			String name = entry.getKey();
//			String value = Arrays.asList(entry.getValue()).stream().collect(Collectors.joining(", "));
//			System.out.printf("name = %s, value = %s\r\n", name, value);
//		});
		System.out.println("==========================================");
	}
}
