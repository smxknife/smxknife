package com.smxknife.dubbo.consumer.annotation.action;

import com.smxknife.dubbo.api.service.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2019/12/11
 */
@Component
public class EchoConsumer {

	@Reference
	private EchoService echoService;

	public String echo(String message) {
		return echoService.echo(message);
	}
}
