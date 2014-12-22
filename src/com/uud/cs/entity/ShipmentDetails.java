package com.uud.cs.entity;

/**
 * 发货详情
 * @author yangl
 *
 */
public class ShipmentDetails {
	private Long id;
	private String shipment_no;
	private String goods_code;
	private String goods_name;
	private String goods_unit;
	private String barcode;
	private Integer goods_qty;
	private Integer actual_qty;
	private String goods_desc;
	private Short yn;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShipment_no() {
		return shipment_no;
	}
	public void setShipment_no(String shipment_no) {
		this.shipment_no = shipment_no;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_unit() {
		return goods_unit;
	}
	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Integer getGoods_qty() {
		return goods_qty;
	}
	public void setGoods_qty(Integer goods_qty) {
		this.goods_qty = goods_qty;
	}
	public Integer getActual_qty() {
		return actual_qty;
	}
	public void setActual_qty(Integer actual_qty) {
		this.actual_qty = actual_qty;
	}
	public String getGoods_desc() {
		return goods_desc;
	}
	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}
	public Short getYn() {
		return yn;
	}
	public void setYn(Short yn) {
		this.yn = yn;
	}
}
