package com.smxknife.http.demo1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Req4XXController {

	@RequestMapping("/4xx")
	public String xx4() {
		return "4xx";
	}

	@RequestMapping("/412")
	@ResponseBody
	public String reqCode() {
		return "4xx";
	}
}
