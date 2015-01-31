package com.uud.cs.entity;

import java.io.Serializable;
import java.util.Date;

public class OrdersDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String orders_no;
	private String product_name;
	private String product_no;
	private Date effective;
	private Long qty;
	private Double bulk;
	private Double weight;
	private Short status;
	private Boolean yn;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrders_no() {
		return orders_no;
	}
	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public Date getEffective() {
		return effective;
	}
	public void setEffective(Date effective) {
		this.effective = effective;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public Double getBulk() {
		return bulk;
	}
	public void setBulk(Double bulk) {
		this.bulk = bulk;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Boolean getYn() {
		return yn;
	}
	public void setYn(Boolean yn) {
		this.yn = yn;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
}
