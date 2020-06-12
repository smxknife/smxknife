package com.smxknife.spring.manual.controller;

import com.smxknife.spring.manual.annotation.Autowired;
import com.smxknife.spring.manual.annotation.Controller;
import com.smxknife.spring.manual.annotation.RequestMapping;
import com.smxknife.spring.manual.annotation.RequestParam;
import com.smxknife.spring.manual.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/30
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("getUser")
	public void getUser(@RequestParam("name")String name, HttpServletRequest req, HttpServletResponse resp) {
		String user = userService.getUser(name);
		try {
			resp.getWriter().write(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
