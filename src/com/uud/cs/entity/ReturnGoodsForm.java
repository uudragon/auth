package com.uud.cs.entity;

import java.util.Date;

public class ReturnGoodsForm {
	
	private Long id;
	private String order_no;
	private Short type;
	private Short result;
	private String reason;
	private Date create_time;
	private String issn;
	private Integer number;
	private Short is_enter;
	private Float refund;
	private String holder;
	private Float commission_charge;
	private String payee;
	private String accounts;
	private String bank;
	private String update_user;
	private String create_user;
	private String form_no;
	private Date update_time;
	
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getForm_no() {
		return form_no;
	}
	public void setForm_no(String form_no) {
		this.form_no = form_no;
	}
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Short getIs_enter() {
		return is_enter;
	}
	public void setIs_enter(Short is_enter) {
		this.is_enter = is_enter;
	}
	public Float getRefund() {
		return refund;
	}
	public void setRefund(Float refund) {
		this.refund = refund;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public Float getCommission_charge() {
		return commission_charge;
	}
	public void setCommission_charge(Float commission_charge) {
		this.commission_charge = commission_charge;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}
