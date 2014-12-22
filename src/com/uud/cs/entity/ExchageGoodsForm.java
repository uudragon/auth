package com.uud.cs.entity;

import java.util.Date;

public class ExchageGoodsForm {
	
	private Long id;
	private String order_no;
	private Short type;
	private Short result;
	private Short status;
	private Short exchange_subject;
	private String reason;
	private Short is_enter;
	private Short send_status;
	private Short send_type;
	private Short holder;
	private Float fare;
	private String issn;
	private Date create_time;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Short getResult() {
		return result;
	}
	public void setResult(Short result) {
		this.result = result;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Short getExchange_subject() {
		return exchange_subject;
	}
	public void setExchange_subject(Short exchange_subject) {
		this.exchange_subject = exchange_subject;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Short getIs_enter() {
		return is_enter;
	}
	public void setIs_enter(Short is_enter) {
		this.is_enter = is_enter;
	}
	public Short getSend_status() {
		return send_status;
	}
	public void setSend_status(Short send_status) {
		this.send_status = send_status;
	}
	public Short getSend_type() {
		return send_type;
	}
	public void setSend_type(Short send_type) {
		this.send_type = send_type;
	}
	public Short getHolder() {
		return holder;
	}
	public void setHolder(Short holder) {
		this.holder = holder;
	}
	public Float getFare() {
		return fare;
	}
	public void setFare(Float fare) {
		this.fare = fare;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
