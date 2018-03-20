package com.smxknife.softmarket.menu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smxknife.softmarket.menu.config.WeChatMenuApi;
import com.smxknife.softmarket.menu.domain.Menu;
import com.smxknife.softmarket.menu.repository.ButtonRepository;
import com.smxknife.softmarket.menu.service.MenuMgrService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MenuMgrServiceImpl implements MenuMgrService {

	Logger logger = Logger.getLogger(MenuMgrServiceImpl.class);

	@Value("${wechat.accessToken.url}")
	private String accessTokenUrl;

	@Autowired
	WeChatMenuApi weChatMenuApi;

	@Autowired
	ButtonRepository buttonRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String create(Menu menu) {
		logger.info("create custom menu");

//		menu.getButtons().forEach(button -> {
//			buttonRepository.saveAndFlush(button);
//			button.getSubButtons().forEach(sub -> {
//				buttonRepository.saveAndFlush(sub);
//			});
//
//		});
//
//		logger.info("persist custom menu success");

		String accessToken = restTemplate.getForObject(accessTokenUrl, String.class);
		String res = null;
		try {
			String btnJson = objectMapper.writeValueAsString(menu);
			res = restTemplate.postForObject(weChatMenuApi.getCreate() + accessToken, btnJson, String.class);
			logger.info("create wechat custom menu: " + res);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}

		return res;
	}

	@Override
	public String delete() {
		String accessToken = restTemplate.getForObject(accessTokenUrl, String.class);
		logger.info("accessToken " + accessToken);
		logger.info("accessTokenUrl " + accessTokenUrl);
		String res = restTemplate.getForObject(weChatMenuApi.getDelete() + accessToken, String.class);
		logger.info(res);
		return res;
	}
}
