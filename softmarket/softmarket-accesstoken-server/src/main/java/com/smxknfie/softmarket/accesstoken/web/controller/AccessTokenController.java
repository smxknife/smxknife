package com.smxknfie.softmarket.accesstoken.web.controller;

import com.smxknfie.softmarket.accesstoken.service.AccessTokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat/accessToken")
public class AccessTokenController {

	private static Logger logger = Logger.getLogger(AccessTokenController.class);

	@Autowired
	AccessTokenService accessTokenService;

	@GetMapping
	public String accessToken() {
		return accessTokenService.accessToken();
	}

}
