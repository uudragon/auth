package com.uud.cs.entity;

import java.util.Date;

public class Customer {
	
	public static final Byte SEX_MAN = 1;
	public static final Byte SEX_WOMAN = 2;
	
	public static final Byte STATUS_NOT_ORDER = 0;
	public static final Byte STATUS_ORDER = 1;
	
	public static final Byte YN_INVALIDITY = 0;
	public static final Byte YN_VALIDITY = 1;
	
	private Long id;
	private String code;
	private Byte type;
	private String name;
	private Byte sex;
	private String birthday;
	private String child;
	private Byte c_sex;
	private String email;
	private String province;
	private String city;
	private String district;
	private String street;
	private String address;
	private String post;
	private String phone;
	private String main_phone;
	private String fax;
	private Byte status;
	private String creator;
	private Date create_time;
	private String updater;
	private Date update_time;
	private Byte yn;
	private Boolean is_allot;
	
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
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public Byte getC_sex() {
		return c_sex;
	}
	public void setC_sex(Byte c_sex) {
		this.c_sex = c_sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
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
	public Byte getYn() {
		return yn;
	}
	public void setYn(Byte yn) {
		this.yn = yn;
	}
	public Boolean getIs_allot() {
		return is_allot;
	}
	public void setIs_allot(Boolean is_allot) {
		this.is_allot = is_allot;
	}
}
