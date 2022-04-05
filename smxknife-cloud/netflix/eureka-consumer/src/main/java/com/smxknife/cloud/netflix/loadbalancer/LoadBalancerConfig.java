package com.smxknife.cloud.netflix.loadbalancer;

import org.springframework.context.annotation.Configuration;

/**
 * @author smxknife
 * 2021/4/29
 */
@Configuration
public class LoadBalancerConfig {

	/**
	 * 负载均衡策略
	 * 只在Hontox版本中支持，在2020版本中不支持
	 * 除了这种方式，还可以通过配置文件进行配置
	 * - 在application.properties
	 *  - <服务名>.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
	 *  - 如：服务名是eureka-provider，eureka-provider.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
	 *
	 *  另外，IRule除了可以配置负载均衡，还可以通过自定义实现IRule实现服务间调用的灰度发布
	 * @return
	 */
//	@Bean
//	public IRule iRule() {
//		IRule rule = null;
//
//		// 随机
//		rule = new RandomRule();
//		// 轮询
//		//rule = new RoundRobinRule();
//		// 可用过滤策略
//		// rule = new AvailabilityFilteringRule();
//
//		return rule;
//	}
}
