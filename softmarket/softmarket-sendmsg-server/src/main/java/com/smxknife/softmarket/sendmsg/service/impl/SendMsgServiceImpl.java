package com.smxknife.softmarket.sendmsg.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smxknife.softmarket.sendmsg.config.WeChatSendMsgApi;
import com.smxknife.softmarket.sendmsg.domain.SendMsg;
import com.smxknife.softmarket.sendmsg.domain.SendResult;
import com.smxknife.softmarket.sendmsg.repository.SendResultRepository;
import com.smxknife.softmarket.sendmsg.service.SendMsgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class SendMsgServiceImpl implements SendMsgService {

	Logger logger = Logger.getLogger(SendMsgServiceImpl.class);

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	WeChatSendMsgApi weChatSendMsgApi;

	@Autowired
	SendResultRepository sendResultRepository;

	@Value("${wechat.accessToken.url}")
	private String accessTokenUrl;

	@Override
	public String sendAll(SendMsg sendMsg) {
		logger.info("send all url test " + weChatSendMsgApi.getSendAll());
		String res = sendWeChat(sendMsg, weChatSendMsgApi.getSendAll());
		logger.info("send all result " + res);
		return res;
	}

	@Override
	public String sendByOpenId(SendMsg sendMsg) {
		logger.info("send openId url test " + weChatSendMsgApi.getOpenId());
		String res = sendWeChat(sendMsg, weChatSendMsgApi.getOpenId());
		logger.info("send openId result " + res);
		return res;
	}

	private String sendWeChat(SendMsg sendMsg, String sendUrl) {
		try {
			String accessToken = restTemplate.getForObject(accessTokenUrl, String.class);
			logger.info("accessToken is " + accessToken);

			String json = objectMapper.writeValueAsString(sendMsg);

			String res = restTemplate.postForObject(sendUrl + accessToken, json, String.class);
			logger.info("send result " + res);

			try {
				SendResult sendResult = objectMapper.readValue(res, SendResult.class);
				sendResult.setSendMsg(json);
				sendResultRepository.save(sendResult);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return res;
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return e.getMessage();
		}
	}
}
