package com.smxknife.springboot._10_retry;

import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2021/8/1
 */
@RestController
@RequestMapping("10/retry")
public class RetryController {

	private AtomicInteger num = new AtomicInteger(0);

	@Retryable(value = Exception.class, maxAttempts = 2)
	@GetMapping
	public String retryTest() throws Exception {
		System.out.println("retry test | " + num.getAndIncrement());
		throw new Exception();
	}
}
