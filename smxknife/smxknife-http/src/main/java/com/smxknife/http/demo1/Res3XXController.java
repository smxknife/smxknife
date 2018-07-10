package com.smxknife.http.demo1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Res3XXController {

	@RequestMapping("300")
	public String res300(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(300);
		return "300";
	}

	@RequestMapping("301")
	public String res301(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(301);
		response.setHeader("Location", "/3002");
		return "3001";
	}

	@RequestMapping("302")
	public String res302(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(302);
		response.setHeader("Location", "/3002");
		return "3001";
	}

	@RequestMapping("304")
	public String res304(HttpServletRequest request, HttpServletResponse response) {
		return "3001";
	}

	@RequestMapping("post")
	public String post() {
		return "post";
	}

	@RequestMapping(value = "post/{code}/action", method = RequestMethod.POST)
	public String post301Action(@PathVariable int code, String name, String password, HttpServletResponse response) {
		response.setStatus(code);
		response.setHeader("Location", "/3002");
		System.out.println(name);
		System.out.println(password);
		return "3001";
	}

	@RequestMapping(value = "get/{code}/action", method = RequestMethod.GET)
	public String get301Action(@PathVariable int code, String name, String password, HttpServletResponse response) {
		response.setStatus(code);
		response.setHeader("Location", "/3002");
		System.out.println(name);
		System.out.println(password);
		return "3001";
	}


	@RequestMapping("3001")
	public String res3001(HttpServletRequest request, HttpServletResponse response) {
		return "3001";
	}

	@RequestMapping("3002")
	public String res3002(HttpServletRequest request, HttpServletResponse response) {
		return "3002";
	}
}
