package com.smxknife.okhttp.demo;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author smxknife
 * 2019-06-14
 */
public class RequestDemoTest {

	@Test
	public void syncRequest() {
		RequestDemo requestDemo = new RequestDemo();
		Assert.assertEquals(200, requestDemo.syncRequest("http://www.baidu.com"));
	}

	@Test
	public void asyncRequestSuccess() {
		RequestDemo requestDemo = new RequestDemo();
		requestDemo.asyncRequest("http://www.baidu.com");
	}
}
