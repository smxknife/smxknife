package com.smxknife.cloud.netflix.web.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author smxknife
 * 2021/4/29
 */
@RestController
@RequestMapping("/client/eureka")
public class EurekaClientController {

	@Autowired
	EurekaClient client;

	/**
	 *
	 * @param serviceId 这里的serviceId不是ClientController里面的service取出结果，而是192.168.32.26:eureka-provider:8000
	 * @return
	 */
	@Deprecated
	@RequestMapping("instances")
	public List<InstanceInfo> getInstances(String serviceId) {
		// 参数：192.168.32.26:eureka-provider:8000
		return client.getInstancesById(serviceId); // 参数写成这个格式肯定是不对的，所以这种方法的使用场景还是比较少的
	}

	/**
	 *
	 * @param serviceId 这个方法里的serviceId就是ClientController里面的service取出来的结果，这里可以是eureka-provider
	 * @return
	 */
	@RequestMapping("instances2")
	public List<InstanceInfo> getInstances2(String serviceId) {
		final List<InstanceInfo> instancesByVipAddress = client.getInstancesByVipAddress(serviceId, false);
		return instancesByVipAddress;
	}

	@RequestMapping("provider/index")
	public Object getProviderIndex() {
		@Deprecated
		final InstanceInfo instanceInfo = this.getInstances("192.168.32.26:eureka-provider:8000").get(0);
		final InstanceInfo instanceInfoNew = this.getInstances2("eureka-provider").get(0);

		final val homePageUrl = instanceInfoNew.getHomePageUrl();
		System.out.println("provider url = " + homePageUrl);
		final RestTemplate restTemplate = new RestTemplate();
		final String indexName = restTemplate.getForObject(homePageUrl + "/index/name", String.class);

		return indexName;
	}
}
