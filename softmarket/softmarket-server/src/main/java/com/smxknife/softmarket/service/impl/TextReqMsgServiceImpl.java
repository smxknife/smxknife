package com.smxknife.softmarket.service.impl;

import com.smxknife.softmarket.common.comm.WeChatConstant;
import com.smxknife.softmarket.common.domain.Article;
import com.smxknife.softmarket.service.ReqMsgService;
import com.smxknife.softmarket.service.RespMsgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(WeChatConstant.TEXT_REQ_MSG_SERVICE)
public class TextReqMsgServiceImpl implements ReqMsgService {

	Logger logger = Logger.getLogger(TextReqMsgServiceImpl.class);

	@Autowired
	@Qualifier(WeChatConstant.TEXT_RESP_MSG_SERVICE)
	RespMsgService textRespMsgService;

	@Autowired
	@Qualifier(WeChatConstant.NEWS_RESP_MSG_SERVICE)
	RespMsgService newsRespMsgService;

	@Override
	public String process(Map<String, String> requestMap) {
		logger.info("processing text message");
		String resContent = "";
		String reqContent = requestMap.get(WeChatConstant.CONTENT);
		switch (reqContent) {
			case WeChatConstant.CMD_1:
				resContent = "您输入了1";
				resContent = textRespMsgService.sendMsg(requestMap, resContent);
				break;
			case WeChatConstant.CMD_2:
				resContent = "您输入了2";
				List<Article> articles = new ArrayList<>();
				Article a1 = new Article();
				a1.setTitle("Java拆箱/装箱原理");
				a1.setDescription("Java拆箱/装箱原理");
				a1.setPicUrl("https://www.imooc.com/static/img/article/cover/pic6.png");
				a1.setUrl("https://www.imooc.com/article/23807");
				articles.add(a1);

				Article a2 = new Article();
				a2.setTitle("Java 序列化");
				a2.setDescription("Java 序列化");
				a2.setPicUrl("https://www.imooc.com/static/img/article/cover/pic1.png");
				a2.setUrl("https://www.imooc.com/article/23808");
				articles.add(a2);

				Article a3 = new Article();
				a3.setTitle("Java 奇数特性及优化方案");
				a3.setDescription("Java 奇数特性及优化方案");
				a3.setPicUrl("https://www.imooc.com/static/img/article/cover/pic5.png");
				a3.setUrl("https://www.imooc.com/article/23488");
				articles.add(a3);

				Article a4 = new Article();
				a4.setTitle("java动态代理，不一样的理解方式");
				a4.setDescription("java动态代理，不一样的理解方式");
				a4.setPicUrl("https://www.imooc.com/static/img/article/cover/pic3.png");
				a4.setUrl("https://www.imooc.com/article/23342");
				articles.add(a4);

				Article a5 = new Article();
				a5.setTitle("Spring Transaction 使用");
				a5.setDescription("Spring Transaction 使用");
				a5.setPicUrl("https://www.imooc.com/static/img/article/cover/pic4.png");
				a5.setUrl("https://www.imooc.com/article/23343");
				articles.add(a5);

				resContent = newsRespMsgService.sendMsg(requestMap, articles);
				break;
			case WeChatConstant.CMD_3:
				resContent = "您输入了3";
				resContent = textRespMsgService.sendMsg(requestMap, resContent);
				break;
			case WeChatConstant.CMD_4:
				resContent = "您输入了4";
				resContent = textRespMsgService.sendMsg(requestMap, resContent);
				break;
				default:
					resContent = "请输入正确指令";
					resContent = textRespMsgService.sendMsg(requestMap, resContent);
					break;
		}
		return resContent;
	}
}
