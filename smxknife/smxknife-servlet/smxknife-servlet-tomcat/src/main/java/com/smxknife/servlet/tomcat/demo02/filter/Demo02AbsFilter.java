package com.smxknife.servlet.tomcat.demo02.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-19
 */
public class Demo02AbsFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println(((HttpServletRequest)servletRequest).getRequestURI());
		System.out.println(((HttpServletRequest)servletRequest).getRequestURL().toString());
		filterChain.doFilter(servletRequest, servletResponse);
		System.out.println(this.getClass().getName());
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(this.getClass().getName() + " filter init");
	}
}
