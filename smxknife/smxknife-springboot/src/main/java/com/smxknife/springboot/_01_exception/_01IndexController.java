package com.smxknife.springboot._01_exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author smxknife
 * 2020/8/25
 */
@Controller
public class _01IndexController {

	@GetMapping("")
	public String index() {
		return "index";
	}
}
