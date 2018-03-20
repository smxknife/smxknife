package com.smxknife.softmarket.service.impl;

import com.smxknife.softmarket.common.comm.RespMsgType;
import com.smxknife.softmarket.common.comm.WeChatConstant;
import com.smxknife.softmarket.service.RespMsgService;
import com.smxknife.softmarket.util.WeChatUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(WeChatConstant.TEXT_RESP_MSG_SERVICE)
public class TextRespMsgServiceImpl implements RespMsgService {
	@Override
	public String sendMsg(Map<String, String> requestMap, Object content) {
		Map<String, Object> responseMap = wrapperResponseMap(requestMap);
		responseMap.put(WeChatConstant.MSG_TYPE, RespMsgType.text.name());
		responseMap.put(WeChatConstant.CONTENT, String.valueOf(content));
		return WeChatUtil.mapToXml(responseMap);
	}
}
