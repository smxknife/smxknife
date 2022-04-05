package com.smxknife.energy.clouds.registry.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/25
 */
@Slf4j
@Component
public class EurekaListener {

	@EventListener
	public void eurekaServerStarted(EurekaServerStartedEvent event) {
		log.info("---- Started : " + event.toString());
	}

	@EventListener
	public void eurekaInstanceCanceled(EurekaInstanceCanceledEvent event) {
		log.info("---- Down : " + event.toString());
	}
}
