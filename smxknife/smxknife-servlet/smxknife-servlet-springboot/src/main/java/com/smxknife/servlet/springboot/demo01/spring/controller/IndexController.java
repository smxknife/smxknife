package com.smxknife.servlet.springboot.demo01.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author smxknife
 * 2019-01-16
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping
	public String index() {
		return "index";
	}

}
