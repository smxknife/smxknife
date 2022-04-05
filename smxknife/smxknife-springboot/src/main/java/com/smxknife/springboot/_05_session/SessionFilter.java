package com.smxknife.springboot._05_session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author smxknife
 * 2021/3/9
 */
@WebFilter
public class SessionFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
		if (Objects.nonNull(session)) {
			session.setAttribute("req", servletRequest);
			session.setAttribute("resp", servletResponse);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
