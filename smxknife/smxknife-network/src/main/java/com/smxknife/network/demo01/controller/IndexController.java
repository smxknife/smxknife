package com.smxknife.network.demo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2019-01-25
 */
@RestController
@RequestMapping("/")
public class IndexController {

	@RequestMapping
	public String index() {
		return "index";
	}

	@RequestMapping("idx")
	public String idx() {
		return "idx";
	}
}
