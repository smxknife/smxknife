package com.smxknfie.jms.demo01;

import javax.jms.*;

public class Chat implements MessageListener {

	private TopicSession session;
	private TopicPublisher publisher;
	private TopicConnection connection;
	private String userName;

	public Chat(String topicFactory, String topicName, String userName) {

	}

	@Override
	public void onMessage(Message message) {

	}
}
