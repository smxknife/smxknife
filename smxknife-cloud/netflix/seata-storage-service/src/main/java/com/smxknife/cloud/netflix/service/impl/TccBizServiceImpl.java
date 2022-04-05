package com.smxknife.cloud.netflix.service.impl;

import com.smxknife.cloud.netflix.service.StorageService;
import com.smxknife.cloud.netflix.service.TccBizService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2021/5/31
 */
@Service
public class TccBizServiceImpl implements TccBizService {

	@Autowired
	private StorageService storageService;

	@Autowired
	private RestTemplate restTemplate;

	// 这里需要注意的是TCC是没有全局事务，是通过协调者来控制各个业务的本地事务完成
//	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void purchase(String userId, String commodityCode, int orderCount, BusinessActionContext context) {
		storageService.deduct(commodityCode, orderCount);

		final String postForObject = restTemplate.postForObject("http://seata-order-service/order/create?userId={1}&commodityCode={2}&orderCount={3}",
				null, String.class, userId, commodityCode, orderCount);
		System.out.println("-------- " + postForObject);
	}

	@Override
	public void purchaseConfirm(BusinessActionContext context) {
		System.out.println("------confirm context " + context );
	}

	@Override
	public void purchaseCancel(BusinessActionContext context) {
		System.out.println("------cancel context " + context );

	}
}
