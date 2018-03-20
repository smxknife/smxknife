package com.smxknife.softmarket.sendmsg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat.api.sendmsg")
public class WeChatSendMsgApi {

//	sendall: ${wechat.api.sendmsg.base}/sendall?access_token=
//	openId: ${wechat.api.sendmsg.base}/send?access_token=
//	get: ${wechat.api.sendmsg.base}/get?access_token=
//	delete: ${wechat.api.sendmsg.base}/delete?access_token=
//	preview: ${wechat.api.sendmsg.base}/preview?access_token=
//	speedget: ${wechat.api.sendmsg.base}/speed/get?access_token=
//	speedSet: ${wechat.api.sendmsg.base}/speed/get?access_token=

	private String sendAll;

	private String openId;

	private String get;

	private String delete;

	private String preview;

	private String speedGet;

	private String speedSet;

	public String getSendAll() {
		return sendAll;
	}

	public void setSendAll(String sendAll) {
		this.sendAll = sendAll;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getGet() {
		return get;
	}

	public void setGet(String get) {
		this.get = get;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getSpeedGet() {
		return speedGet;
	}

	public void setSpeedGet(String speedGet) {
		this.speedGet = speedGet;
	}

	public String getSpeedSet() {
		return speedSet;
	}

	public void setSpeedSet(String speedSet) {
		this.speedSet = speedSet;
	}
}
