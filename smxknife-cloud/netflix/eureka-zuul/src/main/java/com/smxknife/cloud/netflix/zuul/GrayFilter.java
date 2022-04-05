package com.smxknife.cloud.netflix.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 灰度网关过滤
 * @author smxknife
 * 2021/5/27
 */
@Component
public class GrayFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		System.out.println("GrayFilter shouldFilter...");
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		System.out.println("GrayFilter run...");

		// 伪代码
		final RequestContext requestContext = RequestContext.getCurrentContext();

		// 通过SERVICE_ID_KEY可以获取serverId，比如这里要对eureka-provider进行灰度发布，但是对eureka-consumer不执行灰度发布，
		// 那么就可以通过这里对服务名进行排除
		final Object serviceIdKey = requestContext.get(FilterConstants.SERVICE_ID_KEY);

		final HttpServletRequest request = requestContext.getRequest();

		// 从数据库里查询用户规则
		// GrayRule userRule = GrayRuleDao.selectByCode("code");
		// 从规则里获取对应的灰度发布的服务名
		// String serviceId = userRule.getServiceId();
		// 如果请求的服务不是灰度发布的服务，那么直接返回
		// if (!serviceId.eqaual(serviceIdKey)) {
		//      return null;
		// }
		if (!serviceIdKey.equals("enreka-provider")) {
			return null;
		}

		final String userId = request.getHeader("userId");
		// 看看userRule和userId是否匹配
		// if(userRule.match(userId)) {
		RibbonFilterContextHolder.getCurrentContext().add("version", "2");
		// } else {
		// RibbonFilterContextHolder.getCurrentContext().add("version", "1");
		// }
		return null;
	}
}
