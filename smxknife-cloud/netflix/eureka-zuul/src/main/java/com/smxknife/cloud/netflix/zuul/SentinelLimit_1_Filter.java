package com.smxknife.cloud.netflix.zuul;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/29
 */
//@Component
public class SentinelLimit_1_Filter extends ZuulFilter {
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
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// 限流逻辑
		final RequestContext currentContext = RequestContext.getCurrentContext();
		Entry entry = null;
		try {
			entry = SphU.entry("HelloWorld");
			currentContext.set("limit", "false");
			// 业务逻辑
			System.out.println("正常请求");
			// 业务逻辑
		} catch (BlockException e) {
			System.out.println("阻塞了");
			currentContext.set("limit", "true");
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(entry)) {
				entry.exit();
			}
		}
		return null;
	}

	@PostConstruct
	public void init() {
		List<FlowRule> rules = new ArrayList<>();

		FlowRule rule = new FlowRule();
		// 资源名称
		rule.setResource("HelloWorld");
		// 限流类型
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// qps 2
		rule.setCount(2);

		rules.add(rule);

		FlowRuleManager.loadRules(rules);
	}
}
