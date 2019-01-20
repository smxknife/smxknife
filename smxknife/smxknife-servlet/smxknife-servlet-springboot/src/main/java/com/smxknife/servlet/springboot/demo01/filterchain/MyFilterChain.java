package com.smxknife.servlet.springboot.demo01.filterchain;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-02
 */
public class MyFilterChain implements FilterChain {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
		System.out.println(this.getClass().getSimpleName() + " doFilter...");
	}
}
