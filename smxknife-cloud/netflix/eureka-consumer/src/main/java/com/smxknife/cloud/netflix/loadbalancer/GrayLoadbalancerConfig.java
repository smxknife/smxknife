package com.smxknife.cloud.netflix.loadbalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author smxknife
 * 2021/5/27
 */
@Configuration
public class GrayLoadbalancerConfig implements WebMvcConfigurer {

	@Autowired
	private AllRequestHandlerInterceptor requestHandlerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestHandlerInterceptor).addPathPatterns("/**");
	}
}
