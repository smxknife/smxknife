package com.smxknife.softmarket.usermgr.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat/mgr/tags")
public class TagsMgrController {

	@PostMapping("create")
	public String create() {
		return null;
	}

	@GetMapping("mockCreate")
	public String mockCreate() {

		return "";
	}
}
