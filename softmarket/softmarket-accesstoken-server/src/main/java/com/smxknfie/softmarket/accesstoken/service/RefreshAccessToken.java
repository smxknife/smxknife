package com.smxknfie.softmarket.accesstoken.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smxknfie.softmarket.accesstoken.domain.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
final class RefreshAccessToken {

	Logger logger = LoggerFactory.getLogger(RefreshAccessToken.class);

	@Value("${wechat.accessTokenUrl}")
	private String accessTokenUrl;

	@Autowired
	AccessTokenService accessTokenService;

	@Autowired
	private ObjectMapper objectMapper;

	@Scheduled(fixedRate = 5400 * 1000)
	void refresh() throws IOException {
		logger.info("refresh assess token");
		RestTemplate template = new RestTemplate();
		String res = template.getForObject(accessTokenUrl, String.class);
		AccessToken token = objectMapper.readValue(res, AccessToken.class);
		if (token == null) {
			logger.error(res);
		} else {
			accessTokenService.flushAccessToken(token);
		}
	}
}
