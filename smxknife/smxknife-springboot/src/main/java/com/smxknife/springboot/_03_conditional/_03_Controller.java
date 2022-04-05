package com.smxknife.springboot._03_conditional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/2/20
 */
@RestController
public class _03_Controller {

	@Value("${server.sessionTimeout:300}")
	private Long sessionTimeout;

	@RequestMapping("/timeout")
	public Long timeout() {
		return sessionTimeout;
	}
}
