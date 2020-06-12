package com.smxknife.servlet.xml;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author smxknife
 * 2019/12/31
 */
public class ErrorServlet extends HttpServlet {

	private static int num = 0;

	static {
		System.out.println("==== static ==== 初始化");
	}

	public ErrorServlet() {
		System.out.println("==== constructor ==== 初始化");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
		System.out.println("==== service ====");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("==== init() ==== 初始化");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("==== init(config) ==== 初始化");
		super.init(config);
		Enumeration<String> parameterNames = config.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			System.out.println("    ---- parameter: name = " + parameterName);
			System.out.println("    ---- parameter: value = " + config.getInitParameter(parameterName));
		}
		System.out.println("    ---- servletName = " + config.getServletName());
		if (num == 0) {
			num++;
			throw new RuntimeException("模拟第一次初始化失败");
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("==== destroy() ====");
	}
}
