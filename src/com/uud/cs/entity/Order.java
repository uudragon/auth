package com.uud.cs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String order_no;
	private Short order_type;
	private Date effective;
	private Date deadline;
	private String customer_code;
	private String customer_name;
	private String customer_phone;
	private String customer_addr;
	private Boolean has_invoice;
	private Short source;
	private String agent_code;
	private String coupon_code;
	private Double discount_amount;
	private Double amount;
	private Short payment;
	private Short status;
	private Boolean paid;
	private Short validity;
	private Date order_time;
	private String creator;
	private Date create_time;
	private String updater;
	private Date update_time;
	private Integer contact_count;
	private Boolean yn;
	
	private Customer customer;
	
	private List<OrdersDetail> details;

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

	public Short getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Short order_type) {
		this.order_type = order_type;
	}

	public Date getEffective() {
		return effective;
	}

	public void setEffective(Date effective) {
		this.effective = effective;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_addr() {
		return customer_addr;
	}

	public void setCustomer_addr(String customer_addr) {
		this.customer_addr = customer_addr;
	}

	public Boolean getHas_invoice() {
		return has_invoice;
	}

	public void setHas_invoice(Boolean has_invoice) {
		this.has_invoice = has_invoice;
	}

	public Short getSource() {
		return source;
	}

	public void setSource(Short source) {
		this.source = source;
	}

	public String getAgent_code() {
		return agent_code;
	}

	public void setAgent_code(String agent_code) {
		this.agent_code = agent_code;
	}

	public String getCoupon_code() {
		return coupon_code;
	}

	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	public Double getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(Double discount_amount) {
		this.discount_amount = discount_amount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Short getPayment() {
		return payment;
	}

	public void setPayment(Short payment) {
		this.payment = payment;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getValidity() {
		return validity;
	}

	public void setValidity(Short validity) {
		this.validity = validity;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getContact_count() {
		return contact_count;
	}

	public void setContact_count(Integer contact_count) {
		this.contact_count = contact_count;
	}

	public Boolean getYn() {
		return yn;
	}

	public void setYn(Boolean yn) {
		this.yn = yn;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrdersDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrdersDetail> details) {
		this.details = details;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	

}
