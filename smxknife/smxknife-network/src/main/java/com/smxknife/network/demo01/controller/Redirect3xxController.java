package com.smxknife.network.demo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-25
 */
@RestController
@RequestMapping("/")
public class Redirect3xxController {

	@RequestMapping
	public String index() {
		return "index";
	}

	@RequestMapping("idx")
	public String idx() {
		return "idx";
	}

	@RequestMapping("301")
	public String hand301(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "redirect 301:/final?code=301";
	}

	@RequestMapping("302")
	public String hand302(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "redirect 302:/final?code=302";
	}

	@RequestMapping("final")
	public String finalPage(@RequestParam(name = "code") String code) {
		return "this is final page " + code;
	}
}
