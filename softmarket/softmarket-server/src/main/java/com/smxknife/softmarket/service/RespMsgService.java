package com.smxknife.softmarket.service;

import com.smxknife.softmarket.common.comm.WeChatConstant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public interface RespMsgService {

	String sendMsg(Map<String, String> requestMap, Object content);

	default Map<String, Object> wrapperResponseMap(Map<String, String> requestMap) {
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(WeChatConstant.FROM_USER_NAME, requestMap.get(WeChatConstant.TO_USER_NAME));
		responseMap.put(WeChatConstant.TO_USER_NAME, requestMap.get(WeChatConstant.FROM_USER_NAME));
		responseMap.put(WeChatConstant.CREATE_TIME, new Date().getTime());
		return responseMap;
	}
}
