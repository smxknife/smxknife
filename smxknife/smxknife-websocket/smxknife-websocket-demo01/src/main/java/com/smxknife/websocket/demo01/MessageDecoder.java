package com.smxknife.websocket.demo01;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @author smxknife
 * 2019-08-16
 */
public class MessageDecoder implements Decoder.Text<Message> {

	private static Gson gson = new Gson();

	@Override
	public Message decode(String s) throws DecodeException {
		return gson.fromJson(s, Message.class);
	}

	@Override
	public boolean willDecode(String s) {
		return (s != null);
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
