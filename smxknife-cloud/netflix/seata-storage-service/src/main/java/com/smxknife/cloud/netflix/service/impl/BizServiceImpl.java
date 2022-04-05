package com.smxknife.cloud.netflix.service.impl;

import com.smxknife.cloud.netflix.service.BizService;
import com.smxknife.cloud.netflix.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2021/5/31
 */
@Service
public class BizServiceImpl implements BizService {

	@Autowired
	private StorageService storageService;

	@Autowired
	private RestTemplate restTemplate;

	@GlobalTransactional(rollbackFor = Exception.class)
	@Override
	public void purchase(String userId, String commodityCode, int orderCount) {
		storageService.deduct(commodityCode, orderCount);

		final String postForObject = restTemplate.postForObject("http://seata-order-service/order/create?userId={1}&commodityCode={2}&orderCount={3}",
				null, String.class, userId, commodityCode, orderCount);
		System.out.println("-------- " + postForObject);
		//orderService.create(userId, commodityCode, orderCount);
	}
}
