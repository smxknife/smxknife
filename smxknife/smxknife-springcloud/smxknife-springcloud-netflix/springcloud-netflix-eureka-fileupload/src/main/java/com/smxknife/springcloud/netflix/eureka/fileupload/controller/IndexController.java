package com.smxknife.springcloud.netflix.eureka.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author smxknife
 * 2018/9/17
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
