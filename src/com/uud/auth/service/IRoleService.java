package com.uud.auth.service;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRole;
import com.uud.auth.entity.Role;

public interface IRoleService {
	
	public List<ResourceRole> findRolePermissions();
	
	public Long save( Role role );
	
	public Page<Role> findByParams( Map<String, Object> map, int pageSize, int pageNo );
	
	public int update( Role role );
	
	public int delete( Long id );

	public List<Role> findAll();
	
	public List<Resource> findResources( Long roleId );
	
	public void updateResource( Long[] resourceIds , Long roleId );
}
