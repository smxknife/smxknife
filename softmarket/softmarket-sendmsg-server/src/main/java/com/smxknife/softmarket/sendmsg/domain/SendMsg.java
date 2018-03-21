package com.smxknife.softmarket.sendmsg.domain;

import java.util.List;

public class SendMsg {

	private SendType sendType = SendType.sendall;

	private Filter filter;

	private String[] touser;

	private MsgType msgtype;

	private int send_ignore_reprint = 0;

	private Text text;

	private Media media;

	private String mediaLabel;

	public SendMsg() {
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public MsgType getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(MsgType msgtype) {
		this.msgtype = msgtype;
		switch (msgtype) {
			case text:
				this.text = new Text();
				this.mediaLabel = msgtype.name();
				break;
			default:
				this.media = new Media();
				this.mediaLabel = msgtype.name();
				break;
		}
	}

	public int getSend_ignore_reprint() {
		return send_ignore_reprint;
	}

	public void setSend_ignore_reprint(int send_ignore_reprint) {
		this.send_ignore_reprint = send_ignore_reprint;
	}

	public Text getText() {
		return text;
	}

	private void setText(Text text) {
		this.text = text;
	}

	public Media getMedia() {
		return media;
	}

	private void setMedia(Media media) {
		this.media = media;
	}

	public String getMediaLabel() {
		return mediaLabel;
	}

	private void setMediaLabel(String mediaLabel) {
		this.mediaLabel = mediaLabel;
	}

	public SendType getSendType() {
		return sendType;
	}

	public void setSendType(SendType sendType) {
		this.sendType = sendType;
	}

	public String[] getTouser() {
		return touser;
	}

	public void setTouser(String[] touser) {
		this.touser = touser;
	}

	public class Media {

		private String media_id;

		private String title;

		public String getMedia_id() {
			return media_id;
		}

		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}

	public enum SendType {
		sendall, openid
	}

	public enum MsgType {
		mpnews, // 图文消息
		text,
		voice,
		music,
		image,
		video,
		wxcard // 卡券
	}

	public class Text {

		private String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	public class Filter {

		private boolean is_to_all = true;

		List<Long> tag_id;

		public boolean isIs_to_all() {
			return is_to_all;
		}

		public void setIs_to_all(boolean is_to_all) {
			this.is_to_all = is_to_all;
		}

		public List<Long> getTag_id() {
			return tag_id;
		}

		public void setTag_id(List<Long> tag_id) {
			this.tag_id = tag_id;
		}
	}
}
