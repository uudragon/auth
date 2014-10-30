package com.uud.auth.entity;

import java.io.Serializable;

public class ResourceRoleGroup implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private RoleGroup rg;
	
	private Resource resource;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RoleGroup getRg() {
		return rg;
	}
	public void setRg(RoleGroup rg) {
		this.rg = rg;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
}
