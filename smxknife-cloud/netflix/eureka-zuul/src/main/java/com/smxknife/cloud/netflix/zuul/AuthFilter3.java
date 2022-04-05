package com.smxknife.cloud.netflix.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author smxknife
 * 2021/5/26
 */
//@Component
public class AuthFilter3 extends ZuulFilter {
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 2;
	}

	@Override
	public boolean shouldFilter() {
		System.out.println("AuthFilter3 shouldFilter ...");
		final RequestContext currentContext = RequestContext.getCurrentContext();

		return false;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("AuthFilter3 run ...");
		return null;
	}
}
