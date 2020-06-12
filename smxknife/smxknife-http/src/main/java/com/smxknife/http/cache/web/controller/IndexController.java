package com.smxknife.http.cache.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author smxknife
 * 2020/1/9
 */
@Controller
@RequestMapping({"/cache/index", "/cache"})
public class IndexController {

	@RequestMapping
	public String index(HttpServletResponse resp) {
		resp.setHeader("Cache-Control", "public, max-age=3600");
		return "/cache/index";
	}
}
