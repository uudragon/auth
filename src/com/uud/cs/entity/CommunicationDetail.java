package com.uud.cs.entity;

import java.util.Date;

public class CommunicationDetail {
	
	
	//沟通内容类型，1:咨询，2:订购，3:投诉，4:回访，5:建议。
	public static final String CHAT_TYPE_CONSULTATION = "1";
	public static final String CHAT_TYPE_ORDER = "2";
	public static final String CHAT_TYPE_COMPLAIN = "3";
	public static final String CHAT_TYPE_CHECK = "4";
	public static final String CHAT_TYPE_SUGGEST = "5";
	
	//紧急程度  ： 1、一般，2、优先，3、紧急
	public static final int DEAL_LEVEL_1 = 1;
	public static final int DEAL_LEVEL_2 = 2;
	public static final int DEAL_LEVEL_3 = 3;
	
	//电话状态
	public static final String PHONE_STATUS_NOBODY = "0";
	public static final String PHONE_STATUS_BUSY = "1";
	public static final String PHONE_STATUS_ANSWER = "2";
	public static final String PHONE_STATUS_HALT = "3";
	public static final String PHONE_STATUS_SPACE = "4";
	public static final String PHONE_STATUS_WRONG = "5";
	
	private Long id;
	private String custmerCode;
	private String userNo;
	private String chatType;
	private String chatContent;
	private Date startTime;
	private Date entTime;
	private Date nextTime;
	private String dealResult;
	private String telNo;
	private Integer dealLevel;
	private String theme;
	private String complainType;
	private String phoneStatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustmerCode() {
		return custmerCode;
	}
	public void setCustmerCode(String custmerCode) {
		this.custmerCode = custmerCode;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getChatType() {
		return chatType;
	}
	public void setChatType(String chatType) {
		this.chatType = chatType;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEntTime() {
		return entTime;
	}
	public void setEntTime(Date entTime) {
		this.entTime = entTime;
	}
	public Date getNextTime() {
		return nextTime;
	}
	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}
	public String getDealResult() {
		return dealResult;
	}
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public Integer getDealLevel() {
		return dealLevel;
	}
	public void setDealLevel(Integer dealLevel) {
		this.dealLevel = dealLevel;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getComplainType() {
		return complainType;
	}
	public void setComplainType(String complainType) {
		this.complainType = complainType;
	}
	public String getPhoneStatus() {
		return phoneStatus;
	}
	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}
	
	
}
