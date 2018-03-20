package com.smxknife.softmarket.menu.web.controller;

import com.smxknife.softmarket.menu.domain.Button;
import com.smxknife.softmarket.menu.domain.ButtonType;
import com.smxknife.softmarket.menu.domain.Menu;
import com.smxknife.softmarket.menu.service.MenuMgrService;
import com.smxknife.softmarket.common.comm.ButtonKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wechat/mgr/menu")
public class MenuMgrController {

	@Autowired
	private MenuMgrService menuMgrService;

	@PostMapping("create")
	public String create(@RequestParam List<Button> buttons) {
		return "";
	}

	@GetMapping("delete")
	public String delete() {
		return menuMgrService.delete();
	}

	@GetMapping("mockCreate")
	public String mockCreate() {
		// left
		Button leftRoot = new Button();
		leftRoot.setName("推荐");
		List<Button> leftSub = new ArrayList<>();
		leftRoot.setSubButtons(leftSub);

		Button lfSub1 = new Button();
		lfSub1.setName("优秀应用");
		lfSub1.setType(ButtonType.click);
		lfSub1.setKey(ButtonKeyConstant.B0001_TODAY_RECOMMEND);
		lfSub1.setParent(leftRoot);
		leftSub.add(lfSub1);

		Button lfSub2 = new Button();
		lfSub2.setName("技术博文");
		lfSub2.setType(ButtonType.click);
		lfSub2.setKey(ButtonKeyConstant.B0002_TODAY_RECOMMEND);
		lfSub2.setParent(leftRoot);
		leftSub.add(lfSub2);

		// middle
		Button middleRoot = new Button();
		middleRoot.setName("专题");
		List<Button> middleSub = new ArrayList<>();
		middleRoot.setSubButtons(middleSub);

		Button midSub1 = new Button();
		midSub1.setName("应用市场");
		midSub1.setType(ButtonType.view);
		midSub1.setUrl("http://www.smxknife.com/");
		midSub1.setParent(middleRoot);
		middleSub.add(midSub1);

		Button midSub2 = new Button();
		midSub2.setName("技术园地");
		midSub2.setType(ButtonType.view);
		midSub2.setUrl("http://www.smxknife.com/");
		midSub2.setParent(middleRoot);
		middleSub.add(midSub2);

		// right
		Button rightRoot = new Button();
		rightRoot.setName("工具");
		List<Button> rightSub = new ArrayList<>();
		rightRoot.setSubButtons(rightSub);

		Button rtSub1 = new Button();
		rtSub1.setName("形色识花");
		rtSub1.setType(ButtonType.miniprogram);
		rtSub1.setAppId("wx66aed246411799b2");
		rtSub1.setPagePath("pages/xingseapp/index");
		rtSub1.setUrl("http://www.xingseapp.com/");
		rtSub1.setParent(rightRoot);
		rightSub.add(rtSub1);

		Menu menu = new Menu();
		menu.setButtons(new ArrayList<>());
		menu.getButtons().add(leftRoot);
		menu.getButtons().add(middleRoot);
		menu.getButtons().add(rightRoot);

		String res = menuMgrService.create(menu);
		return res;

	}
}
