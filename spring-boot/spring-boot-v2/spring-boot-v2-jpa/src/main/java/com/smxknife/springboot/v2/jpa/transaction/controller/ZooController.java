package com.smxknife.springboot.v2.jpa.transaction.controller;

import com.smxknife.springboot.v2.jpa.transaction.service.ZooInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2019-08-09
 */
@RestController
@RequestMapping("/zoo")
public class ZooController {

	@Autowired
	ZooInitService zooInitService;

	@RequestMapping("normal")
	public String normal() {
		zooInitService.normal();
		return "normal";
	}

	@RequestMapping("nonTranException")
	public String nonTranException() throws Exception {
		zooInitService.nonTranException();
		return "nonTranException";
	}

	@RequestMapping("tranException")
	public String tranException() throws Exception {
		zooInitService.tranException();
		return "tranException";
	}

	@RequestMapping("annWithTranException")
	public String annWithTranException() throws Exception {
		zooInitService.annWithTranException();
		return "annWithTranException";
	}


}
