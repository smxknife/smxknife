package com.smxknife.softmarket.sendmsg.web.controller;

import com.smxknife.softmarket.sendmsg.domain.SendMsg;
import com.smxknife.softmarket.sendmsg.domain.SendResult;
import com.smxknife.softmarket.sendmsg.repository.SendResultRepository;
import com.smxknife.softmarket.sendmsg.service.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/wechat/mgr/sendmsg")
public class SendMsgController {

	@Autowired
	SendMsgService sendMsgService;

	@Autowired
	SendResultRepository sendResultRepository;

	@GetMapping("mockOpenId")
	public String mockSendByOpenId() {
		SendMsg sendMsg = new SendMsg();
		SendMsg.Filter filter = sendMsg.new Filter();
		filter.setIs_to_all(true);
		sendMsg.setFilter(filter);
		sendMsg.setMsgtype(SendMsg.MsgType.text);
		sendMsg.getText().setContent("softmarket 模拟推送" + new Random().nextInt());
		return sendMsgService.sendByOpenId(sendMsg);
	}

	@GetMapping("mockAll")
	public String mockSendAll() {
		SendMsg sendMsg = new SendMsg();
		SendMsg.Filter filter = sendMsg.new Filter();
		filter.setIs_to_all(true);
		sendMsg.setFilter(filter);
		sendMsg.setMsgtype(SendMsg.MsgType.text);
		sendMsg.getText().setContent("softmarket 模拟推送" + new Random().nextInt());

		return sendMsgService.sendAll(sendMsg);
	}

	@GetMapping("mockGetSendMsg")
	public List<SendResult> mockGetSendMsg() {
		return sendResultRepository.findAll();
	}

	@PostMapping("openId")
	public String sendByOpenId(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	@PostMapping("all")
	public String sendAll() {
		return null;
	}
}
