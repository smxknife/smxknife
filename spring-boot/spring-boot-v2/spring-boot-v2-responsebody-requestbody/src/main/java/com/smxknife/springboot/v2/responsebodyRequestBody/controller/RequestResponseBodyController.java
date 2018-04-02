package com.smxknife.springboot.v2.responsebodyRequestBody.controller;

import com.smxknife.springboot.v2.responsebodyRequestBody.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestResponseBodyController {

	Logger logger = LoggerFactory.getLogger(RequestResponseBodyController.class);

	@RequestMapping("/req")
	@ResponseBody
	public User req(@RequestBody User user) {
		logger.info(user.toString());
		user.setName("response");
		user.setAge(0);
		return user;
	}
}
