package com.smxknife.http.offlinecache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author smxknife
 * 2020/5/28
 */
@Controller
@RequestMapping("/off/index")
public class OfflineCacheIndexController {

	@GetMapping
	public String offIndex() {
		return "/offlinecache/offIndex";
	}

	@GetMapping("/tag")
	public String tag() {
		return "/offlinecache/tag";
	}
}
