package com.smxknife.springboot.v1.config.web.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;

//@Controller
public class MyErrorController implements ErrorController {


	@Override
	public String getErrorPath() {
		return "index";
	}

//	@RequestMapping("/error")
	public String error() {
		return getErrorPath();
	}
}
