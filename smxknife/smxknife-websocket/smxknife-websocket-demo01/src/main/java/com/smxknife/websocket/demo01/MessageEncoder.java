package com.smxknife.websocket.demo01;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author smxknife
 * 2019-08-16
 */
public class MessageEncoder implements Encoder.Text<Message> {

	private static Gson gson = new Gson();

	@Override
	public String encode(Message message) throws EncodeException {
		return gson.toJson(message);
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
		// custom init
	}

	@Override
	public void destroy() {
		// close resource
	}
}
