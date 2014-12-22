package com.uud.cs.entity;

import java.util.Date;
/**
 * 发货单
 * @author yangl
 *
 */
public class Shipment {
	
	private Long id;
	
	private String orders_no;
	
	private String shipment_no;
	
	private Integer shipped_qty;
	
	private Short express_code;
	
	private String express_orders_no;
	
	private String express_name;
	
	private Double express_cost;
	
	private String courier;
	
	private String courier_tel;
	
	private Date create_time;
	
	private String creator;
	
	private String update_time;
	
	private String updater;
	
	private Short yn;

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

	public String getShipment_no() {
		return shipment_no;
	}

	public void setShipment_no(String shipment_no) {
		this.shipment_no = shipment_no;
	}

	public Integer getShipped_qty() {
		return shipped_qty;
	}

	public void setShipped_qty(Integer shipped_qty) {
		this.shipped_qty = shipped_qty;
	}

	public Short getExpress_code() {
		return express_code;
	}

	public void setExpress_code(Short express_code) {
		this.express_code = express_code;
	}

	public String getExpress_orders_no() {
		return express_orders_no;
	}

	public void setExpress_orders_no(String express_orders_no) {
		this.express_orders_no = express_orders_no;
	}

	public String getExpress_name() {
		return express_name;
	}

	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}

	public Double getExpress_cost() {
		return express_cost;
	}

	public void setExpress_cost(Double express_cost) {
		this.express_cost = express_cost;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public String getCourier_tel() {
		return courier_tel;
	}

	public void setCourier_tel(String courier_tel) {
		this.courier_tel = courier_tel;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Short getYn() {
		return yn;
	}

	public void setYn(Short yn) {
		this.yn = yn;
	}
}
