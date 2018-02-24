package com.smxknife.softmarket.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/wechat")
public class WechatController {

	private static Logger logger = Logger.getLogger(WechatController.class);

	@Autowired
	SignHelper signHelper;

	@GetMapping("security")
	public void doGet(HttpServletRequest request,
	                     HttpServletResponse response,
	                     @RequestParam(value = "signature", required = true) String signature,
	                     @RequestParam(value = "timestamp", required = true) String timestamp,
	                     @RequestParam(value = "nonce", required = true) String nonce,
	                     @RequestParam(value = "echostr", required = true) String echostr) {
		try {
			logger.info(">>>> signature: " + signature);
			logger.info(">>>> timestamp: " + timestamp);
			logger.info(">>>> nonce: " + nonce);
			logger.info(">>>> echostr: " + echostr);
			if (signHelper.checkSignature(signature, timestamp, nonce)) {
				PrintWriter out = response.getWriter();
				out.print(echostr);
				out.close();
			} else {
				logger.warn("the request is invalid!");
			}
		} catch (IOException e) {
			logger.error(e, e);
		}

	}

	@PostMapping("security")
	public void doPost() {
		logger.info("post method！");
	}

}
