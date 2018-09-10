package com.smxknfie.springcloud.netflix.eureka.ribbon;

import com.smxknfie.springcloud.netflix.eureka.config.UserRibbonConfig;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author smxknife
 * 2018/9/5
 */
@Configuration
// 方法1
@RibbonClient(name = "user-service", configuration = UserRibbonConfig.class)
// 方法2
//@RibbonClient(name = "user-service", configuration = UserRibbonConfig2.class)
//@ComponentScan(excludeFilters = {
//		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ExcludeAnnotation.class)
//})
public class CustomRibbonConfig {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}
}
