package com.smxknife.servlet.springboot.demo04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2020/2/10
 */
@RestController
public class Demo4Controller {

	@Autowired
	private Demo4Interface demo4Interface;

	@Autowired
	private Other4Service other4Service;

	@RequestMapping
	public void test() {
		System.out.println(demo4Interface);
	}

	@RequestMapping("/idx")
	public void index() {
		System.out.println("index");
		demo4Interface.idx();
		other4Service.other();
	}

}
