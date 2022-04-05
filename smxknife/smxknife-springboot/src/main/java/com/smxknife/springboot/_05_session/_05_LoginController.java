package com.smxknife.springboot._05_session;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author smxknife
 * 2021/3/9
 */
@RestController
@RequestMapping("/login")
public class _05_LoginController {

	@RequestMapping
	public String login() {
		return "login";
	}

	@RequestMapping("do")
	public String doLogin(HttpServletRequest request) {
		request.getSession(true);
		return "success";
	}
}
