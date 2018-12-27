package com.smxknife.servlet.springboot.demo02.web.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author smxknife
 * 2018-12-27
 */
@RestController
public class IndexController {

	@RequestMapping("/")
	public String index(HttpServletRequest req, HttpServletResponse resp) {
		Gson gson = new Gson();
		HttpSession session = req.getSession(false);

		if (session == null) session = req.getSession(true);
		return gson.toJson(session);
	}
}
