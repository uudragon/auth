package com.uud.auth.dao;

import java.util.List;

import com.uud.auth.entity.Resource;

public interface IResourceDao {
	public Long save( Resource resource );
	public int update( Resource resource );
	public Resource findById( Long id );
	public List<Resource> findByParams( Resource r );
	public int countByParams( Resource r );
	public List<Resource> findAll();
	public int deleteById( Long id );
	public int deleteRoleResource( Long resourceId );
	public int deleteRoleGroupResource( Long resourceId );
	public List<Resource> findByParent(Long resourceId);
	
}
