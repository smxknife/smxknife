package com.smxknife.cloud.netflix.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/26
 */
@Component
public class AuthFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
//		System.out.println("AuthFilter shouldFilter ...");
		final RequestContext currentContext = RequestContext.getCurrentContext();
//		currentContext.setSendZuulResponse(false);
//		currentContext.setResponseBody("filter1");
		return currentContext.get("limit").equals("false");
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("AuthFilter run ...");
		return null;
	}
}
