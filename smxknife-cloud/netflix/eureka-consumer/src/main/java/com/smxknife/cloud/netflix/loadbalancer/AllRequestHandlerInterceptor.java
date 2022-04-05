package com.smxknife.cloud.netflix.loadbalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author smxknife
 * 2021/5/27
 */
@Component
public class AllRequestHandlerInterceptor implements HandlerInterceptor {

	@Autowired
	private RibbonRequestThreadLocal ribbonRequestThreadLocal;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		final String version = request.getParameter("version");
		ribbonRequestThreadLocal.set(version);
		return true;
	}
}
