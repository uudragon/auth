package com.uud.cs.entity;

import java.util.Date;

public class Message {
	
	public static final byte SENDTYPE_MESSAGE = 1;
	public static final byte SENDTYPE_EMAIL = 2;
	
	public static final byte STATUS_WAITING = 1;
	public static final byte STATUS_SENDING = 2;
	public static final byte STATUS_SENDED = 3;
	public static final byte STATUS_FAILED = 4;
	
	private Long id;
	private String target;
	private Long template;
	private Byte sendType;
	private Date sendTime;
	private Byte status;
	private String message;
	private String opUser;
	
	private String content;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Long getTemplate() {
		return template;
	}
	public void setTemplate(Long template) {
		this.template = template;
	}
	public Byte getSendType() {
		return sendType;
	}
	public void setSendType(Byte sendType) {
		this.sendType = sendType;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOpUser() {
		return opUser;
	}
	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
