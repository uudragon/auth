package com.uud.cs.entity;

import java.util.Date;

public class TaskDetails {
	
	public static final byte TASK_TYPE_CONSULTATION = 1; 
	public static final byte TASK_TYPE_CHECK = 2;
	public static final byte TASK_TYPE_COMPLAIN = 3;
	public static final byte TASK_TYPE_SUGGEST = 4;
	
	public static final int TASK_TYPE_CONTENT_PRODUCT = 20;
	public static final int TASK_TYPE_CONTENT_SERVICE = 21;
	public static final int TASK_TYPE_CONTENT_ORDER = 22;
	public static final int TASK_TYPE_CONTENT_PAY = 23;
	public static final int TASK_TYPE_CONTENT_LOGISTICS = 24;
	
	
	public static final byte TASK_STATUS_WAITING_DEAL = 1;
	public static final byte TASK_STATUS_DEAL = 2;
	public static final byte TASK_STATUS_FINSH = 3;
	
	public static final String CHANNEL_NET = "0";
	public static final String CHANNEL_PHONE= "1";
	
	private Long id;
	private String taskNo;
	private Date taskDate;
	private Byte taskType;
	private	Integer taskTypeContent;
	private Byte taskStatus;
	private String consumerCode;
	private String consumerName;
	private Date hopeDate;
	private Date dealDate;
	private String dealResult;
	private String channel;
	private String userNo;
	private String operUser;
	private String duration;
	private Byte yn;
	private String phone;
	private String content;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	public Byte getTaskType() {
		return taskType;
	}
	public void setTaskType(Byte taskType) {
		this.taskType = taskType;
	}
	public Byte getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Byte taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getConsumerCode() {
		return consumerCode;
	}
	public void setConsumerCode(String consumerCode) {
		this.consumerCode = consumerCode;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public Date getHopeDate() {
		return hopeDate;
	}
	public void setHopeDate(Date hopeDate) {
		this.hopeDate = hopeDate;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public String getDealResult() {
		return dealResult;
	}
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getOperUser() {
		return operUser;
	}
	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Byte getYn() {
		return yn;
	}
	public void setYn(Byte yn) {
		this.yn = yn;
	}
	public Integer getTaskTypeContent() {
		return taskTypeContent;
	}
	public void setTaskTypeContent(Integer taskTypeContent) {
		this.taskTypeContent = taskTypeContent;
	}
	
	
}
