package com.smxknife.flowable.springboot.ui.modeler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2020/11/17
 */
@RestController
@RequestMapping("/index")
public class IndexController {

	@RequestMapping
	public String index() {
		return "index";
	}

}
