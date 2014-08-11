package com.uud.auth.service;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.RoleGroup;

public interface IRoleGroupService {
	
	public Long save( RoleGroup rg );
	
	public Page<RoleGroup> findByPage(Map<String, Object> map, int pageSize,
			int pageNo);
	
	public int update( RoleGroup rg );
	
	public int delete( Long id );

	public List<RoleGroup> findAll();
	
	public List<Resource> findResources( Long roleId );
	
	public void updateResource( Long[] resourceIds , Long roleId );
}
