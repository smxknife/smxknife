package com.smxknife.servlet.tomcat.demo01.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-02
 */
@WebFilter(filterName = "myfilter1", urlPatterns = "/*")
public class MyFilter1 implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("myFilter1 init");
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("myfilter1 doFilter");
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
