package com.smxknife.softmarket.service.impl;

import com.smxknife.softmarket.common.comm.ReqMsgType;
import com.smxknife.softmarket.common.comm.WeChatConstant;
import com.smxknife.softmarket.service.MsgServiceFactory;
import com.smxknife.softmarket.service.ReqMsgService;
import com.smxknife.softmarket.service.WeChatService;
import com.smxknife.softmarket.util.WeChatUtil;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Service
public class WeChatServiceImpl implements WeChatService {

	Logger logger = Logger.getLogger(WeChatServiceImpl.class);

	@Autowired
	MsgServiceFactory msgServiceFactory;

	@Override
	public String processRequest(HttpServletRequest request) {
		String respXml = "";
		try {
			Map<String, String> requestMap = WeChatUtil.parseXml(request);
			String msgType = requestMap.get(WeChatConstant.MSG_TYPE);
			ReqMsgService reqMsgService = msgServiceFactory.getReqMsgServiceImpl(ReqMsgType.valueOf(msgType));
			if (reqMsgService != null) {
				respXml = reqMsgService.process(requestMap);
			}

		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e, e);
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return respXml;
	}


}
