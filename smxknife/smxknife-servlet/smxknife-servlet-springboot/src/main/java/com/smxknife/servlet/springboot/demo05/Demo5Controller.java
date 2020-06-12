package com.smxknife.servlet.springboot.demo05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author smxknife
 * 2020/2/10
 */
@RestController
public class Demo5Controller {

	@Autowired
	private RegEquipmentService regEquipmentService;

	@RequestMapping("/idx")
	public void index(HttpServletRequest request) {
		System.out.println("index");
		regEquipmentService.saveOrUpdate(null, request, 1, (short) 0);
	}

	@RequestMapping("save")
	public String save(Object object) {

		return "hahah success";
	}

}
