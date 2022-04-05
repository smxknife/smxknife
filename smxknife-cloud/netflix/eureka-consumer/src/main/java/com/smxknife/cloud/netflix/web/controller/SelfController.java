package com.smxknife.cloud.netflix.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/5/28
 */
@RestController
@RequestMapping("/self")
public class SelfController {

	@RequestMapping
	public String self() {
		System.out.println("self");
		return "self";
	}
}
