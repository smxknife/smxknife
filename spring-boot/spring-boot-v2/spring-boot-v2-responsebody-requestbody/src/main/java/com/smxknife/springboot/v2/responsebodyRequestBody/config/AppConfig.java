package com.smxknife.springboot.v2.responsebodyRequestBody.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smxknife.springboot.v2.responsebodyRequestBody.component.JsonParseHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	ObjectMapper objectMapper;

	@Bean
	public JsonParseHttpMessageConverter jsonParseHttpMessageConverter() {
		return new JsonParseHttpMessageConverter(objectMapper);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jsonParseHttpMessageConverter());
	}
}
