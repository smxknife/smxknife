package com.smxknife.softmarket.web.controller;

import com.smxknife.softmarket.service.WeChatService;
import com.smxknife.softmarket.util.WeChatUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/wechat")
public class WeChatController {

	private static Logger logger = Logger.getLogger(WeChatController.class);

	@Value("${wechat.token}")
	String token;

	@Autowired
	WeChatService weChatService;

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
			if (WeChatUtil.checkSignature(signature, timestamp, nonce, token)) {
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
	public String doPost(HttpServletRequest request) {
		return weChatService.processRequest(request);
	}


}
