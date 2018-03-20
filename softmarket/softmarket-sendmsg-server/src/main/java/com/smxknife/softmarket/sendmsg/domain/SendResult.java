package com.smxknife.softmarket.sendmsg.domain;

import com.smxknife.softmarket.common.domain.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WECHAT_SEND_MSG_RESULT")
public class SendResult extends Model{

	private String sendMsg;

	private String errcode;

	private String errmsg;

	private String msg_id;

	private String msg_data_id;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_data_id() {
		return msg_data_id;
	}

	public void setMsg_data_id(String msg_data_id) {
		this.msg_data_id = msg_data_id;
	}

	public String getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}
}
