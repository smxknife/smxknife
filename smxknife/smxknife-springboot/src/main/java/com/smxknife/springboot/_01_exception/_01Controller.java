package com.smxknife.springboot._01_exception;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author smxknife
 * 2020/6/6
 */
@Controller
@RequestMapping("/01")
public class _01Controller {

	@GetMapping
	public String index() {
		throw new NotFoundPageException();
	}

	@GetMapping("restful")
	@ResponseBody
	public String restful() {
		throw new RestfulNotFoundException();
	}

	@GetMapping("date")
	@ResponseBody
	public DateTime date(@RequestParam DateTime time) {
		System.out.println(time.getDateTime());
		return time;
	}

	@GetMapping("test")
	public String test(String param) {
		Assert.notNull(param, "参数不能为空");
		return "";
	}
}
