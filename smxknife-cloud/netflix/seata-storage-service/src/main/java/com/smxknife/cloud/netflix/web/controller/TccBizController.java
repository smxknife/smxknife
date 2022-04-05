package com.smxknife.cloud.netflix.web.controller;

import com.smxknife.cloud.netflix.service.TccBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/5/31
 */
@RestController
@RequestMapping("/biz/tcc")
public class TccBizController {

	@Autowired
	private TccBizService bizService;

	@RequestMapping("purchase")
	public String purchase(String userId, String commodityCode, int orderCount) {
		bizService.purchase(userId, commodityCode, orderCount, null);
		return "采购成功";
	}
}
