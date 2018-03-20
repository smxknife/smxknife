package com.smxknife.softmarket.menu.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WECHAT_CUSTOM_BUTTON")
public class Button {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "btn_name")
	private String name;

	@Column(name = "btn_type")
	@Enumerated(EnumType.STRING)
	private ButtonType type;

	private String url;

	@ManyToOne
	@JoinColumn(name = "parentId")
	private Button parent;

	@Column(name = "btn_key")
	private String key;

	private String mediaId;

	private String appId;

	private String pagePath;

	@OneToMany(mappedBy = "parent")
	private List<Button> subButtons;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public List<Button> getSubButtons() {
		return subButtons;
	}

	public void setSubButtons(List<Button> subButtons) {
		this.subButtons = subButtons;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ButtonType getType() {
		return type;
	}

	public void setType(ButtonType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Button getParent() {
		return parent;
	}

	public void setParent(Button parent) {
		this.parent = parent;
	}
}
