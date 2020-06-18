package com.smxknife.drools.demo02.controller;

import com.smxknife.drools.demo02.service.PromoteEdieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author smxknife
 * 2020/6/15
 */
@Controller
@RequestMapping("/promotion")
public class ShopController {

	@Autowired
	private PromoteEdieService promoteEdieService;

	@RequestMapping("/greeting")
	public String greeting() {
		return "index";
	}

	@RequestMapping("/shop")
	public String shop() {
		return "shopping";
	}

	/**
	 * 编辑促销活动
	 * @param money
	 * @param ruleName
	 */
	@RequestMapping(value = "/ediePromote", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void addPromote(String money, String ruleName) {
		promoteEdieService.ediePromoteMap(money, ruleName);
	}

	@RequestMapping(value = "/toShopping", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> toShopping(String money) {
		Map<String, Object> data = promoteEdieService.toShopping(money);
		return data;
	}
}
