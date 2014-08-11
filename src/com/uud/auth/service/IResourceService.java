package com.uud.auth.service;

import java.util.List;

import com.uud.auth.entity.Resource;

public interface IResourceService {
	
	public List<Resource> findAll();
	
	public Long save( Resource r );
	
	public int update( Resource r );
	
	public int delete( Long id );
	
	public Resource findById( Long id );
}
