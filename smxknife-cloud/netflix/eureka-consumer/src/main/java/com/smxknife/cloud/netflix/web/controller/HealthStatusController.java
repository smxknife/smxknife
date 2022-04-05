package com.smxknife.cloud.netflix.web.controller;

import com.smxknife.cloud.netflix.actuator.HealthStatusChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/4/29
 */
@RestController
@RequestMapping("/health/ctl")
public class HealthStatusController {

	@Autowired
	HealthStatusChecker healthStatusChecker;

	@RequestMapping("up")
	public String ctlUp() {
		healthStatusChecker.setUp(true);
		return "health status set to up: real status is " + healthStatusChecker.isUp();
	}

	@RequestMapping("down")
	public String ctlDown() {
		healthStatusChecker.setUp(false);
		return "health status set to down: real status is " + healthStatusChecker.isUp();
	}

	/**
	 * 这种情况下比较常用，比如catch到某种异常，表示该服务存在问题，想让服务下线，那么就可以按照下面的方式
	 * @return
	 */
	@RequestMapping("mock/exception")
	public String ctlException() {
		try {
			int a = 0;
			// 这里mock异常，在catch里面将服务状态设置为down
			int b = 3 / a;
		} catch (Exception e) {
			healthStatusChecker.setUp(false);
		}
		return String.valueOf(healthStatusChecker.isUp());
	}
}
