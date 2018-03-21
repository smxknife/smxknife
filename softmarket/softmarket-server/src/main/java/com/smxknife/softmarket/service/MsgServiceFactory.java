package com.smxknife.softmarket.service;

import com.smxknife.softmarket.common.comm.ReqMsgType;
import com.smxknife.softmarket.common.comm.RespMsgType;
import com.smxknife.softmarket.common.comm.WeChatConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class MsgServiceFactory {

	Logger logger = Logger.getLogger(MsgServiceFactory.class);

	@Autowired
	WebApplicationContext applicationContext;

	/**
	 * 根据消息类型获取请求消息服务实现类型
	 * @param reqMsgType
	 * @return
	 */
	public ReqMsgService getReqMsgServiceImpl(ReqMsgType reqMsgType) {
		ReqMsgService reqMsgService = null;
//		ReqMsgType reqMsgTypeEnum = ReqMsgType.valueOf(msgType);
		switch (reqMsgType) {
			case text:
				reqMsgService = applicationContext.getBean(WeChatConstant.TEXT_REQ_MSG_SERVICE, ReqMsgService.class);
				break;
			case image:
				reqMsgService = applicationContext.getBean(WeChatConstant.IMAGE_REQ_MSG_SERVICE, ReqMsgService.class);
				break;
			case link:
				reqMsgService = applicationContext.getBean(WeChatConstant.LINK_REQ_MSG_SERVICE, ReqMsgService.class);
				break;
			case location:
				reqMsgService = applicationContext.getBean(WeChatConstant.LOCATION_REQ_MSG_SERVICE, ReqMsgService.class);
				break;
			case video:
				reqMsgService = applicationContext.getBean(WeChatConstant.VIDEO_REQ_MSG_SERVICE, ReqMsgService.class);
				break;
			case shortvideo:
				reqMsgService = applicationContext.getBean(WeChatConstant.SHORTVIDEO_REQ_MSG_SERVICE, ReqMsgService.class);
				break;
			case voice:
				reqMsgService = applicationContext.getBean(WeChatConstant.VOICE_REQ_MSG_SERVICE, ReqMsgService.class);
				break;
			case event:
				reqMsgService = applicationContext.getBean(WeChatConstant.EVENT_REQ_MSG_SERVICE, ReqMsgService.class);
				break;
			default:
				logger.error("request MsgType is not validate");
				break;
		}
		return reqMsgService;
	}

	/**
	 * 根据消息类型获取返回消息服务类型
	 * @param respMsgType
	 * @return
	 */
	public RespMsgService getRespMsgServiceImpl(RespMsgType respMsgType) {
		RespMsgService respMsgService = null;
		switch (respMsgType) {
			case text:
				respMsgService = applicationContext.getBean(WeChatConstant.TEXT_RESP_MSG_SERVICE, RespMsgService.class);
				break;
			case news:
				respMsgService = applicationContext.getBean(WeChatConstant.NEWS_RESP_MSG_SERVICE, RespMsgService.class);
				break;
				default:break;
		}
		return respMsgService;
	}


}
