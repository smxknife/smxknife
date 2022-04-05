package com.smxknife.springboot._08_exceptionhandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/8/1
 */
@RestController
@RequestMapping("08/exp")
public class ExceptionController {

	@GetMapping("ex")
	public String ex() throws Exception {
		throw new Exception();
	}

	@GetMapping("rx")
	public String rx() {
		throw new RuntimeException();
	}

	@GetMapping("ill")
	public String ill() {
		throw new IllegalArgumentException();
	}
}
