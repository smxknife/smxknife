package com.smxknife.network.demo01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-25
 */
@Controller
@RequestMapping("/")
public class Redirect3xxController {

	@RequestMapping("301")
	public RedirectView hand301(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RedirectView view = new RedirectView("final");
		view.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		view.addStaticAttribute("code", "301");
		return view;
	}

	@RequestMapping("302")
	public RedirectView hand302(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RedirectView view = new RedirectView("final");
		view.setStatusCode(HttpStatus.MOVED_TEMPORARILY);
		view.addStaticAttribute("code", "302");
		return view;
	}

	@RequestMapping("final")
	@ResponseBody
	public String finalPage(@RequestParam(name = "code") String code) {
		return "this is final page " + code;
	}
}
