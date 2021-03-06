package com.uud.cs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.uud.auth.util.PackagesSchedule;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Short AUDIT_WAITING=1;
	public static final Short AUDIT_DOING=2;
	public static final Short AUDIT_DONE=4;
	public static final Short AUDIT_INVALID=3;
	
	public static final Short STATUS_NORMAL=1;
	public static final Short STATUS_INVALID=2;
	
	public static final Short WORKFLOW_AUDIT=1;
	public static final Short WORKFLOW_SPLIT=2;
	
	public static final Short PAYMENT_BANK=1;
	public static final Short PAYMENT_ZHIFUBAO=2;
	public static final Short PAYMENT_COD=3;
	
	private Long id;
	private String order_no;
	private String order_type;
	private String packag;
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
	private Short paid;
	private Short validity;
	private Date order_time;
	private String creator;
	private Date create_time;
	private String updater;
	private Date update_time;
	private Integer contact_count;
	private Boolean yn;
	private Short audit;
	private Short workflow;
	private String invoice_title;
	
	private String consignee;
	private String province;
	private String city;
	private String district;
	private String street;
	private String address;
	private String post;
	private String phone;
	private String main_phone;
	private String mail;
	private Integer firstsend = 1;
	
	private Boolean split;
	
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMain_phone() {
		return main_phone;
	}

	public void setMain_phone(String main_phone) {
		this.main_phone = main_phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
		Map<String,Object> map = PackagesSchedule.getPackage( order_type );
		this.packag = (String) map.get( "package_name" );
	}

	public String getInvoice_title() {
		return invoice_title;
	}

	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}

	private Customer customer;
	
	private List<OrdersDetail> details;
	
	public Short getAudit() {
		return audit;
	}

	public void setAudit(Short audit) {
		this.audit = audit;
	}

	public Short getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Short workflow) {
		this.workflow = workflow;
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

	public Short getPaid() {
		return paid;
	}

	public void setPaid(Short paid) {
		this.paid = paid;
	}

	public String getPackag() {
		return packag;
	}

	public void setPackag(String packag) {
		this.packag = packag;
	}

	public Boolean getSplit() {
		return split;
	}

	public void setSplit(Boolean split) {
		this.split = split;
	}

	public Integer getFirstsend() {
		return firstsend;
	}

	public void setFirstsend(Integer firstsend) {
		this.firstsend = firstsend;
	}

}
