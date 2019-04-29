package com.smxknife.network.demo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author smxknife
 * 2019-01-27
 */
@RestController
@RequestMapping("/cache/control")
public class CacheControlController {

	@RequestMapping("public")
	public String cacheControlPublic(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Cache-Control", "public");
		System.out.println(".....");
		return "Cache-Control: public";
	}

	@RequestMapping("private")
	public String cacheControlPrivate(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Cache-Control", "private");
		System.out.println("xxxxx");
		return "Cache-Control: private";
	}
}
