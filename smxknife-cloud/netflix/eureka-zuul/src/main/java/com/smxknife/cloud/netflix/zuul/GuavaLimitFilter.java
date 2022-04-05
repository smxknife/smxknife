package com.smxknife.cloud.netflix.zuul;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/28
 */
@Component
public class GuavaLimitFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return -10;
	}

	@Override
	public boolean shouldFilter() {
//		System.out.println("LimitFilter shouldFilter...");
		return false;
	}

	// 2=每秒2个。0.1=10秒1个
	private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);
	@Override
	public Object run() throws ZuulException {

//		System.out.println("LimitFilter run...");

		final RequestContext currentContext = RequestContext.getCurrentContext();
		if (RATE_LIMITER.tryAcquire()) {
			currentContext.set("limit", "false");
			return null;
		} else {
			System.out.println("-------------------------- limit ");
			// 这里是不往route的过滤器走
			currentContext.setSendZuulResponse(false);
			// 如果想要所有的过滤器都不走，需要自定义；然后后面所有的过滤器都需要对这个进行判断
			currentContext.set("limit", "true");

			currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
		}
		return null;
	}
}
