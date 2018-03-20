package com.smxknife.softmarket.sendmsg.service;

import com.smxknife.softmarket.sendmsg.domain.SendMsg;

public interface SendMsgService {

	String sendAll(SendMsg sendMsg);

	String sendByOpenId(SendMsg sendMsg);
}
