package com.smxknife.dubbo.demo.ch02_anno.consumer;

import com.smxknife.dubbo.demo.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2020/11/7
 */
@Component
public class EchoConsumer {

	@Reference
	private EchoService echoService;

	public void echo(String message) {
		echoService.echo(message);
	}
}
