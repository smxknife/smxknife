package com.smxknife.softmarket.menu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat.api.menu")
public class WeChatMenuApi {

	private String create;

	private String get;

	private String delete;

	private String addconditional;

	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
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

	public String getAddconditional() {
		return addconditional;
	}

	public void setAddconditional(String addconditional) {
		this.addconditional = addconditional;
	}
}
