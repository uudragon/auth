package com.uud.cs.entity;

import java.util.Date;

public class KnowledgeBase {
	
	private Long id;
	private String title;
	private String content;
	private String creater;
	private Date update_time;
	private Short type;
	private Integer browse_number;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Integer getBrowse_number() {
		return browse_number;
	}
	public void setBrowse_number(Integer browse_number) {
		this.browse_number = browse_number;
	}
}
