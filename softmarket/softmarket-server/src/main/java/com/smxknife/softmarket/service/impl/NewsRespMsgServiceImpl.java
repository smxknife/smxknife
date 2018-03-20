package com.smxknife.softmarket.service.impl;

import com.smxknife.softmarket.common.comm.RespMsgType;
import com.smxknife.softmarket.common.comm.WeChatConstant;
import com.smxknife.softmarket.common.domain.Article;
import com.smxknife.softmarket.service.RespMsgService;
import com.smxknife.softmarket.util.WeChatUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(WeChatConstant.NEWS_RESP_MSG_SERVICE)
public class NewsRespMsgServiceImpl implements RespMsgService {

	Logger logger = Logger.getLogger(NewsRespMsgServiceImpl.class);

	@Override
	public String sendMsg(Map<String, String> requestMap, Object content) {
		logger.info("response news msg");

		Map<String, Object> responseMap = wrapperResponseMap(requestMap);
		responseMap.put(WeChatConstant.MSG_TYPE, RespMsgType.news.name());
		List<Map<String, Object>> articleMapList = new ArrayList<>();
		try {
			ArrayList<Article> articles = (ArrayList) content;
			for (Article article : articles) {
				Map<String, Object> item = new HashMap<>();
				Map<String, Object> itemCount = new HashMap<>();
				itemCount.put(WeChatConstant.TITLE, article.getTitle());
				itemCount.put(WeChatConstant.DESCRIPTION, article.getDescription());
				itemCount.put(WeChatConstant.PIC_URL, article.getPicUrl());
				itemCount.put(WeChatConstant.URL, article.getUrl());
				item.put(WeChatConstant.ITEM, itemCount);
				articleMapList.add(item);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		responseMap.put(WeChatConstant.ARTICLES, articleMapList);
		responseMap.put(WeChatConstant.ARTICLE_COUNT, articleMapList.size());
		return WeChatUtil.mapToXml(responseMap);
	}
}
