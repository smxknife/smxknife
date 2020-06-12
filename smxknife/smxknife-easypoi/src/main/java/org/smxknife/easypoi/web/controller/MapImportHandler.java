package org.smxknife.easypoi.web.controller;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author smxknife
 * 2019/10/28
 */
@Slf4j
public class MapImportHandler extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

	@Override
	public void setMapValue(Map<String, Object> map, String originKey, Object value) {
		log.info("originKey = {}, value = {}, map = {}", originKey, value, map.keySet());
	}

	@Override
	public Object exportHandler(Map<String, Object> obj, String name, Object value) {
		log.info("exportHandler | originKey = {}, value = {}, map = {}", name, value, obj);
		return super.exportHandler(obj, name, value);
	}


}
