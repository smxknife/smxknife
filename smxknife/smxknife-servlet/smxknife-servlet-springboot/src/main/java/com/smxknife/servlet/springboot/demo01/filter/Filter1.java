package com.smxknife.servlet.springboot.demo01.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author smxknife
 * 2018-12-26
 */
@WebFilter(filterName = "filter1", urlPatterns = "/*")
public class Filter1 implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(this.getClass().getSimpleName() + " init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		System.out.println("filter1===" + httpServletRequest.getRequestURI());
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		System.out.println(this.getClass().getSimpleName() + " destroy");
	}
}
