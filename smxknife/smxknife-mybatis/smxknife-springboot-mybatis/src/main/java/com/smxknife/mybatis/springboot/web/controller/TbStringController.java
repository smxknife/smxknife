package com.smxknife.mybatis.springboot.web.controller;

import com.smxknife.mybatis.springboot.dao.TBStringMapper;
import com.smxknife.mybatis.springboot.model.TBString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2019-07-18
 */
@RestController
@RequestMapping("/tbstring")
public class TbStringController {

	@Autowired
	TBStringMapper tbStringMapper;

	@RequestMapping("save")
	public String save() {
		TBString tbString = new TBString();
		tbString.setcChar("AA B ");
		tbString.setcVarchar("CC DD ");
		tbStringMapper.insert(tbString);
		return "";
	}

	@RequestMapping("show")
	public TBString show() {
		return tbStringMapper.selectByPrimaryKey(1L);
	}
}
