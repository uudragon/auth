package com.uud.cs.entity;

import java.util.Date;

public class WorkForm {
	public static final byte TASK_TYPE_CONSULTATION = 1; 
	public static final byte TASK_TYPE_CHECK = 2;
	public static final byte TASK_TYPE_COMPLAIN = 3;
	public static final byte TASK_TYPE_SUGGEST = 4;
	
	public static final byte TASK_STATUS_WAITING_DEAL = 1;
	public static final byte TASK_STATUS_DEAL = 2;
	public static final byte TASK_STATUS_FINSH = 3;
	
	private Long id;
	private String code;
	private String order_no;
	private Short type;
	private Short subtype;
	private Short level;
	private String phone;
	private String theme;
	private String content;
	private Short status;
	private String consumer_code;
	private String consumer_name;
	private Date create_time;
	private String user;
	private Date next_time;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Short getSubtype() {
		return subtype;
	}
	public void setSubtype(Short subtype) {
		this.subtype = subtype;
	}
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getConsumer_code() {
		return consumer_code;
	}
	public void setConsumer_code(String consumer_code) {
		this.consumer_code = consumer_code;
	}
	public String getConsumer_name() {
		return consumer_name;
	}
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getNext_time() {
		return next_time;
	}
	public void setNext_time(Date next_time) {
		this.next_time = next_time;
	}
}
