package org.smxknife.easypoi.web.controller;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author smxknife
 * 2019/10/28
 */
@Slf4j
public class MapDictHandler implements IExcelDictHandler {
	@Override
	public String toName(String s, Object o, String s1, Object o1) {
		log.info("MapDictHandler Name | {} {} {} {}", s, o ,s1, o1);
		return null;
	}

	@Override
	public String toValue(String s, Object o, String s1, Object o1) {
		log.info("MapDictHandler Value | {} {} {} {}", s, o ,s1, o1);
		return null;
	}
}
