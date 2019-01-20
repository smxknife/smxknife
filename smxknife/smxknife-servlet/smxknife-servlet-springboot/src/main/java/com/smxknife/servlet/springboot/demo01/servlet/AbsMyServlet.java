package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2018-12-26
 */
public abstract class AbsMyServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println(this.getClass().getSimpleName() + " init..." + " : instance: " + this);
		super.init();
		System.out.println("init parameter" + this.getInitParameter("msg"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("instance：" + this);
		System.out.println("get method");
		System.out.println("=================================");
		System.out.println("schema " + req.getScheme());
		System.out.println("uri " + req.getRequestURI());
		System.out.println("pathInfo " + req.getPathInfo());
		System.out.println("contextPath " + req.getContextPath());
		System.out.println("contextPath " + req.getServletContext().getContextPath());
		System.out.println("pathTranslated " + req.getPathTranslated());
		System.out.println("queryString " + req.getQueryString());
		System.out.println("servletContextName " + req.getServletContext().getServletContextName());
		System.out.println("servletPath " + req.getServletPath());
		// java.lang.IllegalStateException: Cannot change session ID. There is no session associated with this request.
//		System.out.println("changeSessionId " + req.changeSessionId());
		System.out.println("requestedSessionId " + req.getRequestedSessionId());
		System.out.println("characterEncoding " + req.getCharacterEncoding());
		System.out.println("contentType " + req.getContentType());
		System.out.println("serverPort " + req.getServerPort());
		System.out.println("serverName " + req.getServerName());
		System.out.println("remoteUser  " + req.getRemoteUser());
		System.out.println("remoteHost " + req.getRemoteHost());
		System.out.println("remotePort " + req.getRemotePort());
		System.out.println("localPort " + req.getLocalPort());
		System.out.println("localName " + req.getLocalName());
		System.out.println("method " + req.getMethod());
		System.out.println("dispatcherType " + req.getDispatcherType().name());
		System.out.println("locale displayName " + req.getLocale().getDisplayName());
		System.out.println("contentLengthLong " + req.getContentLengthLong());
		System.out.println("userPrincipal.name " + req.getUserPrincipal().getName());
		System.out.println("cookies " + req.getCookies());
		System.out.println("requestUrl " + req.getRequestURL().toString());
//		System.out.println(" " + );
		System.out.println("=================================");
		resp.getWriter().println(this.getClass().getSimpleName() + req.getRequestURI());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post method");
		doGet(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service method");
		super.service(req, resp);
	}

	@Override
	public void destroy() {
		System.out.println(this.getClass().getSimpleName() + " destroy...");
	}
}
